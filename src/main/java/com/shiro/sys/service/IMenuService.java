package com.shiro.sys.service;

import com.shiro.sys.common.Res;
import com.shiro.sys.pojo.Menu;

public interface IMenuService {

	Res add(Menu menu);
	
	/**
	 * 返回全部1级菜单
	 * @return
	 */
	Res listFirstClass();
	
	/**
	 * 返回1级菜单下的全部2级菜单
	 * @param classNum
	 * @return
	 */
	Res listSecondClass(Integer menuId);
	
	Res info(Integer roleId);
}
