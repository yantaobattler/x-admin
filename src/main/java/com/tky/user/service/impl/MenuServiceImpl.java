package com.tky.user.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tky.common.vo.Result;
import com.tky.user.entity.Menu;
import com.tky.user.entity.Role;
import com.tky.user.entity.User;
import com.tky.user.mapper.MenuMapper;
import com.tky.user.mapper.RoleMapper;
import com.tky.user.mapper.UserMapper;
import com.tky.user.service.MenuService;
import com.tky.user.service.RoleService;
import com.tky.user.service.UserService;
import com.tky.user.vo.RoleQuery;
import com.tky.user.vo.UserQuery;
import com.tky.zhanpin.entity.Zhanpin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;


	@Override
	public List<Menu> getMenuList() {
		return menuMapper.getMenuList();
	}

	@Override
	public Long countMenuList() {
		return menuMapper.countMenuList();
	}
	
	
	@Override
	public Result<Object> chgMenudisabled(String id, boolean disable) {
		
		Menu menu = new Menu();
		menu.setSys_menu_id(Integer.parseInt(id));
		if	(disable) {
			menu.setDisabled("0");
		} else {
			menu.setDisabled("1");
		}

		menuMapper.chgMenudisabled(menu);
		
		return Result.success();
	}
	
	
	
	@Override
	public Menu getMenubyid(String id) {
		Menu menu = menuMapper.getMenubyid(id);
		menu.setSys_menu_id(Integer.parseInt(id));
		return menu;
	}
	
	
	
	@Override
	public Result<Object> addMenu(Menu menu){
		
		String icon = menu.getIcon();
		if (icon.startsWith("fa")) {
			icon = "fa " + icon;			
		} else if (icon.startsWith("layui-icon")) {
			icon = "layui-icon " + icon;			
		}
		menu.setIcon(icon);
		
		menu.setDisabled("0");

        menuMapper.addMenu(menu);
		
		return Result.success("新增菜单成功！");
	}
	
	
	
	@Override
	public Result<Object> editMenu(Menu menu){
		
		menu.setDisabled("0");

        menuMapper.editMenu(menu);
		
		return Result.success("修改菜单成功！");
	}
	
	
	
	@Override
	public Result<Object> deleteMenu(String id) {
		
		Long i = menuMapper.countMenubypid(id);
		
		if (i>0) {
			return Result.fail("请先删除子菜单！");
		}
		
		menuMapper.deleteRoleMenubyMenu(id);
		menuMapper.deleteMenu(id);
		
		return Result.success("删除菜单成功！");
	}
	
//	
//	
//	@Override
//	public Result<Object> addRole(Role role){
//
////        System.out.println(role);
//        role.setDisabled("0");
//		roleMapper.addRole(role);
//		
//		return Result.success("新增角色成功！");
//	}
	
	
	
	
//	
//	
//	
//	@Override
//    public Result<Object> addUser(User user) {
//		
//		UserQuery param = new UserQuery();
//		param.setUsername(user.getUsername());
//		if (countUserList(param)>0) {
//			return Result.fail("该用户已存在！");
//		}
//		
//		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		user.setPassword(passwordEncoder.encode("123456"));
//     
//        user.setCreate_time(new Date());
//        
//        user.setDisabled("0");
//        
//		userMapper.addUser(user);
//		
//		return Result.success("新增用户成功！");
//    }
//	
//	
//	@Override
//    public Result<Object> chgUserdisabled(String user_id, boolean disable) {
//		
//		User user = new User();
//		user.setUser_id(Integer.parseInt(user_id));
//		if	(disable) {
//			user.setDisabled("0");
//		} else {
//			user.setDisabled("1");
//		}
//
//		userMapper.chgUserdisabled(user);
//		
//		return Result.success();
//    }
//	
//	
//	@Override
//    public Result<Object> rstpwd(String user_id) {
//		User user = new User();
//		user.setUser_id(Integer.parseInt(user_id));
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		user.setPassword(passwordEncoder.encode("123456"));
//		userMapper.rstpwd(user);
//    	return Result.success();
//    }

}
