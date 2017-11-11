package com.shiro.sys.service;

import java.util.List;

import com.shiro.sys.common.Res;

public interface IRoleMenuService {

	Res add(Integer roleId, List<Integer> menuIdList);
	
	Res update(Integer roleId, List<Integer> menuIdList);
	
	Res delete(Integer roleId);
}
