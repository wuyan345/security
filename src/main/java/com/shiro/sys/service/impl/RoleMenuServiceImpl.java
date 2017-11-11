package com.shiro.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiro.sys.common.BaseException;
import com.shiro.sys.common.Res;
import com.shiro.sys.dao.RoleMenuMapper;
import com.shiro.sys.service.IRoleMenuService;

@Service("iRoleMenuService")
@Transactional
public class RoleMenuServiceImpl implements IRoleMenuService {

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public Res add(Integer roleId, List<Integer> menuIdList) {
		try{
			roleMenuMapper.batchInsert(roleId, menuIdList);
		}catch (Exception e) {
			throw new BaseException("角色菜单关系添加失败", e);
		}
		return Res.successMsg("角色菜单关系添加成功");
	}

	@Override
	public Res update(Integer roleId, List<Integer> menuIdList) {
		try {
			// 删除所有对应的角色菜单关系
			roleMenuMapper.batchDelete(roleId, menuIdList);
			// 若没有要修改的菜单，直接返回
			if(menuIdList.isEmpty())
				return Res.successMsg("角色菜单关系修改成功");
			roleMenuMapper.batchInsert(roleId, menuIdList);
			return Res.successMsg("角色菜单关系修改成功");
		} catch (Exception e) {
			throw new BaseException("角色菜单关系修改失败", e);
		}
	}

	@Override
	public Res delete(Integer roleId) {
		try {
			roleMenuMapper.batchDelete(roleId);
			return Res.successMsg("角色菜单关系删除成功");
		} catch (Exception e) {
			throw new BaseException("角色菜单关系删除失败", e);
		}
	}
}
