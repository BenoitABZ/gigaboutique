package com.gigaboutique.gigauserservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gigaboutique.gigauserservice.configuration.SecurityConstant;
import com.gigaboutique.gigauserservice.configuration.UserConfiguration;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private SecurityConstant sc;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/utilisateur/signup", "/utilisateur/login").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), sc, utilisateurDao))
				.addFilterBefore(new JWTAuthorizationFilter(sc), UsernamePasswordAuthenticationFilter.class);
	}

}
