package com.tky.user.mapper;

import java.util.List;

import com.tky.user.entity.Role;
import com.tky.user.vo.RoleQuery;

public interface RoleMapper {

	List<Role> getRoleList(RoleQuery param);

	Long countRoleList(RoleQuery param);

	void addRole(Role role);

}
