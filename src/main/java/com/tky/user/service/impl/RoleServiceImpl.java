package com.tky.user.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.tky.common.vo.Result;
import com.tky.user.entity.Menu;
import com.tky.user.entity.Role;
import com.tky.user.entity.User;
import com.tky.user.mapper.RoleMapper;
import com.tky.user.mapper.UserMapper;
import com.tky.user.service.RoleService;
import com.tky.user.service.UserService;
import com.tky.user.vo.RoleQuery;
import com.tky.user.vo.UserQuery;
import com.tky.zhanpin.entity.Zhanpin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    
    @Resource
    private UserMapper userMapper;


	@Override
	public List<Role> getRoleList(RoleQuery param) {
		return roleMapper.getRoleList(param);
	}

	@Override
	public Long countRoleList(RoleQuery param) {
		return roleMapper.countRoleList(param);
	}
	
	
	@Override
	public Result<Object> addRole(Role role){

//        System.out.println(role);
        role.setDisabled("0");
		roleMapper.addRole(role);
		
		return Result.success("新增角色成功！");
	}

	
	@Override
	public Result<Object> editroleuserByuser(String roletree, String usertree) {
		
		JSONArray data = JSON.parseArray(roletree);
		ArrayList<Integer> role_ids = new ArrayList<Integer>();
		for (int i = 0; i < data.size(); i++) {
			JSONObject obj = JSON.parseObject(data.getString(i));
			role_ids.add(obj.getInteger("id"));
		}
				
		JSONArray data1 = JSON.parseArray(usertree);
		ArrayList<Integer> user_ids = new ArrayList<Integer>();
		for (int i = 0; i < data1.size(); i++) {
			JSONObject obj = JSON.parseObject(data1.getString(i));
			user_ids.add(obj.getInteger("id"));
		}
		
//		System.out.println(role_ids);
//		System.out.println(user_ids);
		
		for (int user_id : user_ids) {
			roleMapper.deleteroleuserByuser(user_id);
			for (int role_id : role_ids) {
				roleMapper.addroleuser(user_id, role_id);
			}
		}
		
		return Result.success("用户角色调整成功！");
	}

	@Override
	public List<?> getusertreeByrole(int role_id) {
		List<Object> usertree = new ArrayList();
		List <?> myusers = roleMapper.getmyusers(role_id);
		List <User> allusers = userMapper.getAllUser();
		for (User user : allusers) {
			Map<String,Object> tmp = new HashMap<>();
			tmp.put("title", user.getChinesename());
			tmp.put("id", user.getUser_id());
			if ( myusers.contains(user.getUser_id())) {
				tmp.put("checked", true);
			} 
			usertree.add(tmp);
		}
		
    	return usertree;
	}

	@Override
	public List<?> getroletreeByrole(int role_id) {
		List<Object> roletree = new ArrayList();
		List <Role> allroles = roleMapper.getAllRoles();
		for (Role role : allroles) {
			Map<String,Object> tmp = new HashMap<>();
			tmp.put("title", role.getRole_name());
			tmp.put("id", role.getRole_id());
			if ( role_id == role.getRole_id()) {
				tmp.put("checked", true);
			} else {
				tmp.put("disabled", true);
			}
			roletree.add(tmp);
		}
		
    	return roletree;
	}
	
	
	@Override
	public Result<Object> editroleuserByrole(String roletree, String usertree) {
		
		JSONArray data = JSON.parseArray(roletree);
		ArrayList<Integer> role_ids = new ArrayList<Integer>();
		for (int i = 0; i < data.size(); i++) {
			JSONObject obj = JSON.parseObject(data.getString(i));
			role_ids.add(obj.getInteger("id"));
		}
				
		JSONArray data1 = JSON.parseArray(usertree);
		ArrayList<Integer> user_ids = new ArrayList<Integer>();
		for (int i = 0; i < data1.size(); i++) {
			JSONObject obj = JSON.parseObject(data1.getString(i));
			user_ids.add(obj.getInteger("id"));
		}
		
		System.out.println(role_ids);
		System.out.println(user_ids);
		
		for (int role_id : role_ids) {
			roleMapper.deleteroleuserByrole(role_id);
			for (int user_id : user_ids) {
				roleMapper.addroleuser(user_id, role_id);
			}
		}
		
		return Result.success("用户角色调整成功！");
	}

	@Override
	public List<?> getmenutreeByrole(int role_id) {
//		List<Object> menutree = new ArrayList();
//		List <?> mymenu = roleMapper.getmymenus(role_id);
//		List <Menu> allmenus = menuMapper.getAllUser();
//		for (Menu menu : allmenus) {
//			Map<String,Object> tmp = new HashMap<>();
//			tmp.put("title", user.getChinesename());
//			tmp.put("id", user.getUser_id());
//			if ( myusers.contains(user.getUser_id())) {
//				tmp.put("checked", true);
//			} 
//			usertree.add(tmp);
//		}
		
    	return null;
	}
	
	
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
