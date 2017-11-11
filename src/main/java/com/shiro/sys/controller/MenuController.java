package com.shiro.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.sys.common.Res;
import com.shiro.sys.pojo.Menu;
import com.shiro.sys.service.IMenuService;

@RestController
@RequestMapping("/sys/menu")
public class MenuController {

	@Autowired
	private IMenuService iMenuService;
	
	@RequestMapping("/add")
	@RequiresPermissions("sys:menu:add")
	public Res add(Menu menu){
		return iMenuService.add(menu);
	}
	
	/**
	 * 返回全部、1级或2级菜单
	 * @param
	 * @return
	 */
	@RequestMapping("/list/{classNum}")
	@RequiresPermissions("sys:menu:add")
	public Res list(@PathVariable Integer classNum){
		if(classNum == 0){
			// 返回全部菜单
			iMenuService.list();
		}else if(classNum == 1){
			// 返回全部1级菜单
			
		}
		return null;
	}
}
