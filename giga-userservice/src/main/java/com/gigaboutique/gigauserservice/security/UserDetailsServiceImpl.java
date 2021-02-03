package com.gigaboutique.gigauserservice.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UtilisateurDao utilisateurDao;

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		UtilisateurBean utilisateur = utilisateurDao.findByMail(mail);
		if (utilisateur == null)
			throw new UsernameNotFoundException(mail);
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(utilisateur.getRole().getRole()));

		return new User(utilisateur.getMail(), utilisateur.getMotDePasse(), authorities);
	}
}
