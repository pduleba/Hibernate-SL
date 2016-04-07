package com.pduleba.spring.dao;

import java.util.List;

import com.pduleba.hibernate.model.Role;

public interface RoleDao {
	
    void addRole(Role role);
    
    void deleteRole(Role role);
     
    List<Role> getAllRoles();
 
    Role getRoleById(long id);
    
    Role getRoleByName(String roleName);
    
    void updateRole(Role role);

	void deleteAllRoles();

}
