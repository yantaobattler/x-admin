package com.tky.user.service.impl;

import org.springframework.stereotype.Service;

import com.tky.user.entity.User;
import com.tky.user.mapper.UserMapper;
import com.tky.user.service.UserService;
import com.tky.user.vo.UserQuery;

import java.util.List;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

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

}
