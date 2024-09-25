package com.tky.user.mapper;

import java.util.List;

import com.tky.user.entity.Role;
import com.tky.user.entity.User;
import com.tky.user.vo.RoleQuery;

public interface RoleMapper {

	List<Role> getRoleList(RoleQuery param);

	Long countRoleList(RoleQuery param);

	void addRole(Role role);

	List<?> getmyroles(int user_id);

	List<Role> getAllRoles();

	void deleteroleuserByuser(int user_id);

	void addroleuser(int user_id, int role_id);

	List<?> getmyusers(int role_id);

	void deleteroleuserByrole(int role_id);

	List<?> getmymenus(int role_id);

	void deleterolemenuByrole(int role_id);

	void addrolemenu(int menu_id, int role_id);


}
