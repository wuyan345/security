package com.shiro.sys.service;


import com.shiro.sys.common.Res;

public interface IRoleGroupService {

	/**
	 * 添加角色部门关系
	 * @param roleId
	 * @param groupId
	 * @return
	 */
	Res add(Integer roleId, Integer groupId);
	
	/**
	 * 修改角色部门关系
	 * @param roleId
	 * @param groupId
	 * @return
	 */
	Res update(Integer roleId, Integer groupId);
	
	/**
	 * 删除角色部门关系
	 * @param roleId
	 * @return
	 */
	Res delete(Integer roleId);
	
}
