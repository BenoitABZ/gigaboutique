package com.gigaboutique.gigauserservice.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gigaboutique.gigauserservice.configuration.SecurityConstantConfiguration;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	SecurityConstantConfiguration scc;

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			UtilisateurBean creds = new ObjectMapper().readValue(req.getInputStream(), UtilisateurBean.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getMail(), creds.getMotDePasse()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException {

		User springUser = (User) auth.getPrincipal();

		String token = Jwts.builder()
				.setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + scc.getExpiration()))
				.signWith(SignatureAlgorithm.RS512, scc.getSecret())
				.claim("role", springUser.getAuthorities())
				.compact();

		String body = (springUser + " " + token);

		res.getWriter().write(body);
		res.getWriter().flush();
	}
}
