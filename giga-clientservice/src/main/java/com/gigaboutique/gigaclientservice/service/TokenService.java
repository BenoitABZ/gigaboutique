package com.gigaboutique.gigaclientservice.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigaclientservice.configuration.SecurityConstant;

@Service
public class TokenService {
	
	@Autowired
	SecurityConstant sc;
	
	public void storeToken(HttpServletResponse response, String tokenBefore) {
		
		String tokenAfter = tokenBefore.replace(sc.getTokenPrefix() + "", "").trim();
		
		System.out.println(tokenAfter);
		
		Cookie cookie = new Cookie("token", tokenAfter);	
		cookie.setMaxAge(864000);
		response.addCookie(cookie);
	}
	
	public void deleteToken(HttpServletRequest request, HttpServletResponse response, String tokenBefore) {
		
        Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			
			if(cookie.getName().equals("token")) {
				
				cookie.setMaxAge(0);
				response.reset();
				response.addCookie(cookie);
			}
		}
	}
	
	public String getToken(HttpServletRequest request) {
	
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			
			if(cookie.getName().equals("token")) {
				
				String tokenBefore = cookie.getValue();
				
				String tokenAfter = sc.getTokenPrefix() + "" +  tokenBefore;
				
				return tokenAfter;
			}
		}
		return null;
	}

}
