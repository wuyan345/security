package com.shiro.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiro.sys.common.exception.BaseException;
import com.shiro.sys.common.Res;
import com.shiro.sys.dao.RoleMapper;
import com.shiro.sys.pojo.Group;
import com.shiro.sys.pojo.Menu;
import com.shiro.sys.pojo.Role;
import com.shiro.sys.service.IGroupService;
import com.shiro.sys.service.IMenuService;
import com.shiro.sys.service.IRoleGroupService;
import com.shiro.sys.service.IRoleMenuService;
import com.shiro.sys.service.IRoleService;

@Service("iRoleService")
@Transactional
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private IRoleGroupService iRoleGroupService;
	@Autowired
	private IRoleMenuService iRoleMenuService;
	@Autowired
	private IMenuService iMenuService;
	@Autowired
	private IGroupService iGroupService;

	@Override
	public Res add(Role role, Integer groupId, List<Integer> menuIdList) {
		try {
			roleMapper.insert(role);
			// 添加角色部门关系
			if(groupId != null)
				iRoleGroupService.add(role.getId(), groupId);
			// 添加角色菜单关系
			if(!menuIdList.isEmpty())
				iRoleMenuService.add(role.getId(), menuIdList);
		} catch (Exception e) {
			throw new BaseException("角色添加失败", e);
		}
		return Res.successMsg("角色添加成功");
	}

	@Override
	public Res edit(Role role) {
		try {
			roleMapper.updateByPrimaryKeySelective(role);
		} catch (Exception e) {
			throw new BaseException("角色修改失败", e);
		}
		return Res.successMsg("角色修改成功");
	}

	@Override
	public Res edit(Integer roleId, Integer groupId) {
		try {
			iRoleGroupService.update(roleId, groupId);
			return Res.successMsg("重新分配角色部门成功");
		} catch (Exception e) {
			throw new BaseException("重新分配角色部门失败", e);
		}
	}

	@Override
	public Res edit(Integer roleId, List<Integer> menuIdList) {
		try {
			iRoleMenuService.update(roleId, menuIdList);
			return Res.successMsg("重新分配角色菜单成功");
		} catch (Exception e) {
			throw new BaseException("重新分配角色菜单失败", e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Res<Object> info(Integer roleId) {
		try {
			// 获取部门信息
			Res<Group> res1 = iGroupService.info(roleId);
			// 获取菜单权限信息
			Res<List<Menu>> res2 = iMenuService.info(roleId);
			// 装配返回数据
			Res res3 = Res.successMsg("获取角色信息成功");
			res3.put("groupInfo", res1.get("data"));
			res3.put("menuList", res2.get("data"));
			return res3;
		} catch (Exception e) {
			throw new BaseException("获取角色信息失败", e);
		}
	}

	@Override
	public Res delete(Integer[] roleIdArray) {
		try {
			for (Integer roleId : roleIdArray) {
				
				// 删除与指定role对应的角色部门关系
				iRoleGroupService.delete(roleId);
				
				// 删除与指定role对应的角色菜单关系
				iRoleMenuService.delete(roleId);
				
				// 删除指定role
				roleMapper.deleteByPrimaryKey(roleId);
			}
			return Res.successMsg("删除角色成功");
		} catch (Exception e) {
			throw new BaseException("删除角色失败", e);
		}
	}
	
}
