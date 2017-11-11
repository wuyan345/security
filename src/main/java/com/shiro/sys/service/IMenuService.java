package com.shiro.sys.service;

import com.shiro.sys.common.Res;
import com.shiro.sys.pojo.Menu;

public interface IMenuService {

	Res add(Menu menu);
	
	Res list();
	
	Res info(Integer roleId);
}
