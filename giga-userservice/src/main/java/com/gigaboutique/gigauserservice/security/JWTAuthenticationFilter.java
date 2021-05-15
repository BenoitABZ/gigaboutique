package com.gigaboutique.gigauserservice.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigauserservice.configuration.SecurityConstant;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UtilisateurDao utilisateurDao;

	private SecurityConstant sc;
	
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SecurityConstant sc,  UtilisateurDao utilisateurDao) {
		this.authenticationManager = authenticationManager;
		this.sc = sc;
		this.utilisateurDao=utilisateurDao;

		setFilterProcessesUrl("/utilisateur/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			UtilisateurBean creds = new UtilisateurBean();
			
			String mail = req.getParameter("mail");
			
			if(mail.contains("%40")) {
				
			mail.replace("%40", "@");
			
			}

			creds.setMail(mail);

			creds.setMotDePasse(req.getParameter("motDePasse"));

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getMail(), creds.getMotDePasse()));
		} catch (NullPointerException npe) {

			res.setHeader("message", "Vos identitfiants(adresse courriel ou mot de passe) sont invalides");
			
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Vos identitfiants(adresse courriel ou mot de passe) sont invalides");

		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
		try {
						
			User springUser = (User) auth.getPrincipal();
									
			long expiration = Long.parseLong(sc.getExpiration());
			
			String secret = sc.getSecret();
			
			List<String> roles = new ArrayList<>();
			
			Collection<GrantedAuthority> authorities = springUser.getAuthorities();
			
			for(GrantedAuthority authority : authorities) {
				
				roles.add(authority.getAuthority());
			}
			
			int idUtilisateur = 0;
					
			UtilisateurBean utilisateur = utilisateurDao.findByMail(springUser.getUsername());
			
			idUtilisateur = utilisateur.getId();
											
			String token = Jwts.builder()
					.setSubject(springUser.getUsername())					
					.setExpiration(new Date(System.currentTimeMillis() + expiration))
					.signWith(SignatureAlgorithm.HS512, secret.getBytes())
					.claim("roles", roles)
					.claim("id", idUtilisateur)
					.compact();

			res.addHeader(sc.getHeader(), sc.getTokenPrefix() + "" + token);

		} catch (NullPointerException npe) {

		}
	}
}
