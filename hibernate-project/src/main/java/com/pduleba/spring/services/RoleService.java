package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.Role;

public interface RoleService {
	
	Role getRoleById(long id);
	
	Role getRoleByName(String roleName);

	void addRole(Role role);

	void updateRole(Role role);

	void deleteRole(Role role);

	List<Role> getAllRoles();
}
