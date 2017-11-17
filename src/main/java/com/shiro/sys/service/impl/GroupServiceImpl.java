package com.shiro.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiro.sys.common.Res;
import com.shiro.sys.common.exception.BaseException;
import com.shiro.sys.dao.GroupMapper;
import com.shiro.sys.pojo.Group;
import com.shiro.sys.service.IGroupService;

@Service("iGroupService")
@Transactional
public class GroupServiceImpl implements IGroupService {

	@Autowired
	private GroupMapper groupMapper;
	
	@Override
	@Transactional(readOnly=true)
	public Res info(Integer roleId) {
		try {
			Group group = groupMapper.selectByPrimaryKey(roleId);
			return Res.successData(group);
		} catch (Exception e) {
			throw new BaseException("获取部门信息失败", e);
		}
	}

}
