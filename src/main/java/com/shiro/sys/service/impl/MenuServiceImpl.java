package com.shiro.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiro.sys.common.BaseException;
import com.shiro.sys.common.Res;
import com.shiro.sys.dao.MenuMapper;
import com.shiro.sys.pojo.Menu;
import com.shiro.sys.service.IMenuService;

@Service("iMenuService")
@Transactional
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	@Transactional(readOnly=true)
	public Res info(Integer roleId) {
		try {
			List<Menu> menuList = menuMapper.selectByRoleId(roleId);
			return Res.successData(menuList);
		} catch (Exception e) {
			throw new BaseException("获取菜单权限信息失败", e);
		}
	}

}
