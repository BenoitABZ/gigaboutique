package com.gigaboutique.gigauserservice.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("role-configs")
public class RoleConfiguration {
	
	private String userRole;
	
	private String adminRole;
	
	private String adminMail;
	
	private List<String> adminPassword;

	public List<String> getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(List<String> administrateur) {
		this.adminPassword = administrateur;
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

}
