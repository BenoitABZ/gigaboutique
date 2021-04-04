
package com.gigaboutique.gigauserservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigauserservice.model.RoleBean;

@Repository
public interface RoleDao extends JpaRepository<RoleBean, Integer> {

	public RoleBean findByRole(String role);

}
