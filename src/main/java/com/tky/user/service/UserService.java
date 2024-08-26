package com.tky.user.service;

import com.tky.user.entity.User;

public interface UserService {
    public User login(User user);

	public User chgUserinfo(User user);

	public void chgpwd(User user);
}
