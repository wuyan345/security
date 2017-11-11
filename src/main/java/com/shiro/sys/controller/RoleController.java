package com.shiro.sys.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.sys.common.Res;
import com.shiro.sys.pojo.Role;
import com.shiro.sys.service.IRoleService;

@RestController
@RequestMapping("/sys/role")
public class RoleController {
	
	@Autowired
	private IRoleService iRoleService;
	
	/**
	 * 添加角色，并分配部门与菜单权限
	 * @param role
	 * @param groupId
	 * @return
	 */
	@RequiresPermissions("sys:role:add")
	@RequestMapping(value="/add")
	public Res add(@RequestBody Role role, Integer groupId, List<Integer> menuIdList){
		return iRoleService.add(role, groupId, menuIdList);
	}
	
	/**
	 * 修改角色信息
	 * @param role
	 * @param groupId
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="/edit")
	public Res edit(Role role){
		return iRoleService.edit(role);
	}
	
	/**
	 * 为角色重新分配部门
	 * @param roleId
	 * @param groupId
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="/editGroup")
	public Res edit(Integer roleId, Integer groupId){
		return iRoleService.edit(roleId, groupId);
	}
	
	/**
	 * 为角色重新分配菜单权限
	 * @param roleId
	 * @param menuIdList
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="/editMenu")
	public Res edit(Integer roleId, List<Integer> menuIdList){
		return iRoleService.edit(roleId, menuIdList);
	}
	
	/**
	 * 获取角色信息，包括其部门、菜单权限
	 * @param roleId
	 * @return
	 */
//	@RequiresPermissions("sys:role:info")
	@RequestMapping(value="/info/{roleId}")
	public Res<Object> info(@PathVariable Integer roleId){
		return iRoleService.info(roleId);
	}
	
	/**
	 * 删除1个或多个角色
	 * @param roleIdArray
	 * @return
	 */
	@RequiresPermissions("sys:role:delete")
	@RequestMapping(value="/delete")
	public Res delete(Integer[] roleIdArray){
		System.out.println(roleIdArray.length);
		for (Integer integer : roleIdArray) {
			System.out.println(integer);
		}
		return iRoleService.delete(roleIdArray);
	}

}
