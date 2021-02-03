package com.gigaboutique.gigauserservice.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gigaboutique.gigauserservice.configuration.SecurityConstant;
import com.gigaboutique.gigauserservice.configuration.UserConfiguration;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	SecurityConstant sc;

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SecurityConstant sc) {
		this.authenticationManager = authenticationManager;
		this.sc = sc;

		setFilterProcessesUrl("/login/utilisateur");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			UtilisateurBean creds = new UtilisateurBean();

			creds.setMail(req.getParameter("mail"));

			creds.setMotDePasse(req.getParameter("motDePasse"));

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getMail(), creds.getMotDePasse()));
		} catch (NullPointerException npe) {

			throw new RuntimeException(npe);

		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException {
		try {
						
			User springUser = (User) auth.getPrincipal();
			
			System.out.println(auth.getPrincipal());
			
			System.out.println(auth.getAuthorities());
			
			System.out.println(springUser.getUsername());
			
			System.out.println(sc.getExpiration());
									
			long expiration = Long.parseLong(sc.getExpiration());
			
			String secret = sc.getSecret();
			
			String token = Jwts.builder()
					.setSubject(springUser.getUsername())
					.setExpiration(new Date(System.currentTimeMillis() + expiration))
					.signWith(SignatureAlgorithm.HS512, secret.getBytes())
					.claim("roles", springUser.getAuthorities())
					.compact();

			res.addHeader(sc.getHeader(), sc.getTokenPrefix() + " " + token);

		} catch (NullPointerException npe) {

		}
	}
}
