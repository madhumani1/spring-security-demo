package com.madhu.springsecurity.demo.dao;

import com.madhu.springsecurity.demo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
