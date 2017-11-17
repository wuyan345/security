package com.shiro.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.sys.common.Res;
import com.shiro.sys.common.annotation.Log;
import com.shiro.sys.pojo.Menu;
import com.shiro.sys.service.IMenuService;

@RestController
@RequestMapping("/sys/menu")
public class MenuController {

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private IMenuService iMenuService;
	
	/**
	 * 添加菜单
	 */
	@RequestMapping("/add")
	@RequiresPermissions("sys:menu:add")
	@Log("添加菜单")
	public Res add(Menu menu){
		return iMenuService.add(menu);
	}
	
	/**
	 * 返回全部1级菜单或一个2级菜单
	 * @param
	 * @return
	 */
	@RequestMapping("/list/{menuId}/{status}")
	@RequiresPermissions("sys:menu:list")
	public Res list(@PathVariable Integer menuId, @PathVariable Integer status){
		logger.info("list, menuId:{}, status:{}", menuId, status);
		if(menuId == 0 && status == 0){
			// 返回全部1级菜单
			return iMenuService.listFirstClass();
		}else if(menuId > 0 && status == 1){
			// 返回2级菜单
			return iMenuService.listSecondClass(menuId);
		}else {
			return Res.errorMsg("参数错误");
		}
	}
	
	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping("/edit")
	@RequiresPermissions("sys:menu:edit")
	@Log("修改菜单")
	public Res edit(Menu menu){
		return null;
	}
	
	/**
	 * 删除1个或多个菜单
	 * @param menuId
	 * @return
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	@Log("删除菜单")
	public Res delete(Integer[] menuId){
		return null;
	}
}
