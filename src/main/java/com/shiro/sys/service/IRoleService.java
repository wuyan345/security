package com.shiro.sys.service;

import java.util.List;

import com.shiro.sys.common.Res;
import com.shiro.sys.pojo.Role;

public interface IRoleService {

	Res add(Role role, Integer groupId, List<Integer> menuIdList);
	
	Res edit(Role role);
	
	Res edit(Integer roleId, Integer groupId);
	
	Res edit(Integer roleId, List<Integer> menuIdList);
	
	Res<Object> info(Integer roleId);
	
	Res delete(Integer[] roleIdArray);
}
