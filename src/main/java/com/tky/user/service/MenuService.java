package com.tky.user.service;

import java.util.List;

import com.tky.common.vo.Result;
import com.tky.user.entity.Menu;
import com.tky.user.entity.Role;
import com.tky.user.vo.RoleQuery;

public interface MenuService {

	List<Menu> getMenuList();

	Long countMenuList();

	Result<Object> chgMenudisabled(String id, boolean disable);

	Menu getMenubyid(String id);

	Result<Object> addMenu(Menu menu);

	Result<Object> editMenu(Menu menu);

	Result<Object> deleteMenu(String id);


	

}
