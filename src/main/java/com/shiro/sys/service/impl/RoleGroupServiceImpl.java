package com.shiro.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiro.sys.common.exception.BaseException;
import com.shiro.sys.common.Res;
import com.shiro.sys.dao.RoleGroupMapper;
import com.shiro.sys.pojo.RoleGroup;
import com.shiro.sys.service.IRoleGroupService;

@Service("iRoleGroupService")
@Transactional
public class RoleGroupServiceImpl implements IRoleGroupService {

	@Autowired
	private RoleGroupMapper roleGroupMapper;
	
	@Override
	public Res add(Integer roleId, Integer groupId) {
		try{
			RoleGroup roleGroup = new RoleGroup();
			roleGroup.setRoleId(roleId);
			roleGroup.setGroupId(groupId);
			roleGroupMapper.insert(roleGroup);
		}catch (Exception e) {
			throw new BaseException("角色部门关系添加失败", e);
		}
		return Res.successMsg("角色部门关系添加成功");
	}

	@Override
	public Res update(Integer roleId, Integer groupId) {
		try {
			RoleGroup roleGroup = new RoleGroup();
			roleGroup.setRoleId(roleId);
			roleGroup.setGroupId(groupId);
			roleGroupMapper.updateByPrimaryKey(roleGroup);
			return Res.successMsg("角色部门关系修改成功");
		} catch (Exception e) {
			throw new BaseException("角色部门关系修改失败", e);
		}
	}

	@Override
	public Res delete(Integer roleId) {
		try {
			roleGroupMapper.deleteByRoleId(roleId);
			return Res.successMsg("角色部门关系删除成功");
		} catch (Exception e) {
			throw new BaseException("角色部门关系删除失败", e);
		}
	}

}
