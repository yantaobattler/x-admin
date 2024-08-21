package com.tky.user.service.impl;

import org.springframework.stereotype.Service;

import com.tky.user.entity.User;
import com.tky.user.mapper.UserMapper;
import com.tky.user.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.getUser(user);
    }
}
