package com.tky.user.mapper;

import com.tky.user.entity.User;

public interface UserMapper {
    public User getUser(User user);

	public void chgUserinfo(User user);

	public void chgpwd(User user);
}
