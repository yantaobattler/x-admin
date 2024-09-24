package com.tky.user.service;

import java.util.List;

import org.springframework.ui.Model;

import com.tky.common.vo.Result;
import com.tky.user.entity.User;
import com.tky.user.vo.UserQuery;

public interface UserService {
	
    public User login(User user);

	public User chgUserinfo(User user);

	public void chgpwd(User user);

	public List<User> getUserList(UserQuery param);

	public Long countUserList(UserQuery param);

	public Result<Object> addUser(User user);

	public Result<Object> chgUserdisabled(String user_id, boolean disable);

	public Result<Object> rstpwd(String user_id);

	public List<?> getusertreeByuser(int user_id);

	public List<?> getroletreeByuser(int user_id);


}
