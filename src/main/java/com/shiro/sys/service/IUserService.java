package com.shiro.sys.service;

import java.util.Map;

import com.shiro.sys.common.Res;
import com.shiro.sys.pojo.User;

public interface IUserService {

	Map test();
	
	Res login(String username, String password);
	
	Res list(int pageNum);
	
	Res add(User user, Integer roleId);
	
	Res edit(User user);
	
	Res info(Integer userId);
	
	Res delete(Integer[] userIdArray);
	
	Res changePassword(Integer userId, String newPassword);
	
	Res logout();
}
