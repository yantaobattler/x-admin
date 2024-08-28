package com.tky.user.service;

import java.util.List;

import com.tky.user.entity.User;
import com.tky.user.vo.UserQuery;

public interface UserService {
	
    public User login(User user);

	public User chgUserinfo(User user);

	public void chgpwd(User user);

	public List<User> getUserList(UserQuery param);

	public Long countUserList(UserQuery param);
}
