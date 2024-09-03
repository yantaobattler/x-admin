package com.tky.user.service;

import java.util.List;

import com.tky.user.entity.Role;
import com.tky.user.vo.RoleQuery;

public interface RoleService {

	List<Role> getRoleList(RoleQuery param);

	Long countRoleList(RoleQuery param);
	

}
