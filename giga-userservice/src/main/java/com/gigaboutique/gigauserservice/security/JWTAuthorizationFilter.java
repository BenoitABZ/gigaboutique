package com.gigaboutique.gigauserservice.security;

import java.io.IOException;
import java.util.*;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gigaboutique.gigauserservice.configuration.SecurityConstantConfiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	SecurityConstantConfiguration scc;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request- Headers,authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			
			String jwtToken = null;
			
			try {
			jwtToken = request.getHeader(scc.getHeader());
			}catch(NullPointerException npe) {
				
				System.out.println("ici");
			}
			if (jwtToken == null || !jwtToken.startsWith(scc.getTokenPrefix())) {
				chain.doFilter(request, response);
				return;
			}
			Claims claims = Jwts.parser().setSigningKey(scc.getSecret()).parseClaimsJws(jwtToken.replace(scc.getTokenPrefix(), "")).getBody();
			String mail = claims.getSubject();
			ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(r -> {
				authorities.add(new SimpleGrantedAuthority(r.get("authority")));
			});

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(mail, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			chain.doFilter(request, response);
		}

	}
}