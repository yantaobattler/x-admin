package com.tky.user.service;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.tky.common.vo.Result;
import com.tky.user.entity.Role;
import com.tky.user.entity.User;
import com.tky.user.vo.RoleQuery;

public interface RoleService {

	List<Role> getRoleList(RoleQuery param);

	Long countRoleList(RoleQuery param);

	Result<Object> addRole(Role role);

	Result<Object> editroleuserByuser(String roletree, String usertree);

	List<?> getusertreeByrole(int role_id);

	List<?> getroletreeByrole(int role_id);

	Result<Object> editroleuserByrole(String roletree, String usertree);

	List<?> getmenutreeByrole(int role_id);

	Result<Object> editrolemenuByrole(String roletree, String menutree);

	JSONObject getInitmenu(User user);
	

}
