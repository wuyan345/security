package com.shiro.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiro.sys.common.exception.BaseException;
import com.shiro.sys.common.Res;
import com.shiro.sys.dao.UserRoleMapper;
import com.shiro.sys.pojo.UserRole;
import com.shiro.sys.service.IUserRoleService;

@Service("iUserRoleService")
@Transactional
public class UserRoleServiceImpl implements IUserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public Res add(Integer userId, Integer roleId) {
		try{
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			userRoleMapper.insert(userRole);
			return Res.successMsg("角色部门关系添加成功");
		}catch (Exception e) {
			throw new BaseException("角色部门关系添加失败", e);
		}
	}

	@Override
	public Res delete(Integer userId) {
		try {
			userRoleMapper.deleteByPrimaryKey(userId);
			return Res.successMsg("角色部门关系删除成功");
		} catch (Exception e) {
			throw new BaseException("角色部门关系删除失败", e);
		}
	}

}
