package com.shiro.sys.service;

import com.shiro.sys.common.Res;

public interface IUserRoleService {

	Res add(Integer userId, Integer roleId);
	
	Res delete(Integer userId);
}
