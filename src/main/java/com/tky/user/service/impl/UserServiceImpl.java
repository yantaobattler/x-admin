package com.tky.user.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tky.common.vo.Result;
import com.tky.user.entity.User;
import com.tky.user.entity.Role;
import com.tky.user.mapper.RoleMapper;
import com.tky.user.mapper.UserMapper;
import com.tky.user.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public User login(User user) {
        return userMapper.getUser(user);
    }
    
    @Override
    public User chgUserinfo(User user) {
    	userMapper.chgUserinfo(user);
    	return userMapper.getUser(user);
    }
    
    @Override
    public void chgpwd(User user) {
    	userMapper.chgpwd(user);
    }

	@Override
	public List<User> getUserList(UserQuery param) {
		return userMapper.getUserList(param);
	}

	@Override
	public Long countUserList(UserQuery param) {
		return userMapper.countUserList(param);
	}
	
	
	@Override
    public Result<Object> addUser(User user) {
		
		UserQuery param = new UserQuery();
		param.setUsername(user.getUsername());
		if (countUserList(param)>0) {
			return Result.fail("该用户已存在！");
		}
		
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode("123456"));
     
        user.setCreate_time(new Date());
        
        user.setDisabled("0");
        
		userMapper.addUser(user);
		
		return Result.success("新增用户成功！");
    }
	
	
	@Override
    public Result<Object> chgUserdisabled(String user_id, boolean disable) {
		
		User user = new User();
		user.setUser_id(Integer.parseInt(user_id));
		if	(disable) {
			user.setDisabled("0");
		} else {
			user.setDisabled("1");
		}

		userMapper.chgUserdisabled(user);
		
		return Result.success();
    }
	
	
	@Override
    public Result<Object> rstpwd(String user_id) {
		User user = new User();
		user.setUser_id(Integer.parseInt(user_id));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode("123456"));
		userMapper.rstpwd(user);
    	return Result.success();
    }
	
	
	@Override
    public List<?> getusertreeByuser(int user_id) {
		List<Object> usertree = new ArrayList();
		List <User> allusers = userMapper.getAllUser();
		for (User user : allusers) {
			Map<String,Object> tmp = new HashMap<>();
			tmp.put("title", user.getChinesename());
			tmp.put("id", user.getUser_id());
			if ( user_id == user.getUser_id()) {
				tmp.put("checked", true);
			} else {
				tmp.put("disabled", true);
			}
			usertree.add(tmp);
		}
		
    	return usertree;
    }

	@Override
	public List<?> getroletreeByuser(int user_id) {
		List<Object> roletree = new ArrayList();
		List <?> myroles = roleMapper.getmyroles(user_id);
		List <Role> allroles = roleMapper.getAllRoles();
		for (Role role : allroles) {
			Map<String,Object> tmp = new HashMap<>();
			tmp.put("title", role.getRole_name());
			tmp.put("id", role.getRole_id());
			if ( myroles.contains(role.getRole_id())) {
				tmp.put("checked", true);
			} 
			roletree.add(tmp);
		}
		
    	return roletree;
	}

}
