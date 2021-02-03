package com.gigaboutique.gigauserservice.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gigaboutique.gigauserservice.configuration.SecurityConstant;
import com.gigaboutique.gigauserservice.configuration.UserConfiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
	
	SecurityConstant sc;
	
	public JWTAuthorizationFilter(SecurityConstant sc) {
		super();
		this.sc = sc;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {

			String jwtToken = null;

			try {
				jwtToken = request.getHeader(sc.getHeader());
			} catch (NullPointerException npe) {

			}
			if (jwtToken == null || !jwtToken.startsWith(sc.getTokenPrefix())) {
				chain.doFilter(request, response);
				return;
			}

			Claims claims = Jwts.parser().setSigningKey(sc.getSecret().getBytes()).parseClaimsJws(jwtToken.replace(sc.getTokenPrefix() + " ", "")).getBody();			
			String mail = claims.getSubject();	
			ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(r -> {
				authorities.add(new SimpleGrantedAuthority(r.get("authority")));
			});

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(mail,null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			chain.doFilter(request, response);
		}
	}
}
