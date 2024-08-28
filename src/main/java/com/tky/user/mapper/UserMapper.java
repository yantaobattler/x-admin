package com.tky.user.mapper;

import java.util.List;

import com.tky.user.entity.User;
import com.tky.user.vo.UserQuery;

public interface UserMapper {
    public User getUser(User user);

	public void chgUserinfo(User user);

	public void chgpwd(User user);

	public Long countUserList(UserQuery param);

	public List<User> getUserList(UserQuery param);
}
