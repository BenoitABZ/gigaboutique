package com.gigaboutique.gigauserservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.gigaboutique.gigauserservice.dao.RoleDao;
import com.gigaboutique.gigauserservice.model.RoleBean;

@Component
@ConfigurationProperties("user-configs")
public class UserConfiguration {

	@Autowired
	RoleDao roleDao;

	private String userRole;

	private String adminRole;

	private String adminMail;

	private RoleBean roleBeanAdmin;

	private RoleBean roleBeanUser;

	private String tokenPrefix;

	private String expiration;

	private String header;

	private String secret;

	public void init() {

		RoleBean roleBeanAdminVerify = null;
		RoleBean roleBeanUserVerify = null;

		try {

			roleBeanAdminVerify = roleDao.findByRole(getAdminRole());

		} catch (NullPointerException npe) {

		}

		if (roleBeanAdminVerify == null) {

			roleBeanAdmin = new RoleBean();

			roleBeanAdmin.setRole(getAdminRole());

		} else {

			roleBeanAdmin = roleBeanAdminVerify;
		}

		try {

			roleBeanUserVerify = roleDao.findByRole(getUserRole());

		} catch (NullPointerException npe) {

		}

		if (roleBeanUserVerify == null) {

			roleBeanUser = new RoleBean();

			roleBeanUser.setRole(getUserRole());

		} else {

			roleBeanUser = roleBeanUserVerify;
		}

	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	public String getAdminMail() {
		return adminMail;
	}

	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}

	public RoleBean getRoleBeanAdmin() {
		return roleBeanAdmin;
	}

	public void setRoleBeanAdmin(RoleBean roleBeanAdmin) {
		this.roleBeanAdmin = roleBeanAdmin;
	}

	public RoleBean getRoleBeanUser() {
		return roleBeanUser;
	}

	public void setRoleBeanUser(RoleBean roleBeanUser) {
		this.roleBeanUser = roleBeanUser;
	}

	public String getTokenPrefix() {
		return tokenPrefix;
	}

	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
