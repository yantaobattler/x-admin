package com.tky.user.mapper;

import java.util.List;

import com.tky.user.entity.Menu;
import com.tky.user.entity.Role;
import com.tky.user.vo.RoleQuery;

public interface MenuMapper {

	List<Menu> getMenuList();

	Long countMenuList();

	void chgMenudisabled(Menu menu);

	Menu getMenubyid(String id);

	void addMenu(Menu menu);

	void editMenu(Menu menu);

	Long countMenubypid(String id);

	void deleteRoleMenubyMenu(String id);

	void deleteMenu(String id);

}
