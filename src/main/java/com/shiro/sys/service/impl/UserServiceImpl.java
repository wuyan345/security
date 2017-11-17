package com.shiro.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.sys.common.exception.BaseException;
import com.shiro.sys.common.Res;
import com.shiro.sys.common.annotation.Log;
import com.shiro.sys.dao.UserMapper;
import com.shiro.sys.pojo.User;
import com.shiro.sys.service.IUserRoleService;
import com.shiro.sys.service.IUserService;

@Service("iUserService")
@Transactional
public class UserServiceImpl implements IUserService {

	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IUserRoleService iUserRoleService;
	
	@Override
	public Map<?, ?> test() {
		System.out.println("进入UserServiceImpl.test()");
		return null;
	}

	@Override
	public Res login(String username, String password) {
		
		Subject currentUser = SecurityUtils.getSubject();

//		Session session = currentUser.getSession();
//		session.setAttribute("someKey", "aValue");
//		String value = (String) session.getAttribute("someKey");
//		if (value.equals("aValue")) {
//			logger.info("Retrieved the correct value! [" + value + "]");
//		}

		if (!currentUser.isAuthenticated()) {
			
			// 若没有登录，则进行登录
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				logger.error("用户:{}不存在", token.getPrincipal());
				return Res.errorMsg("用户:" + token.getPrincipal() + "不存在");
			} catch (IncorrectCredentialsException ice) {
				logger.error("密码错误");
				return Res.errorMsg("密码错误");
			} catch (LockedAccountException lae) {
				logger.error("账户:{}被锁", token.getPrincipal());
				return Res.errorMsg("账户:" + token.getPrincipal() + "被锁");
			}
			catch (AuthenticationException ae) {
				logger.error("unkown error");
				return Res.errorMsg("未知错误");
			}
		}
		
		// 已登录，直接返回
		return Res.successMsg("登录成功");
	}

	@Override
	@Transactional(readOnly=true)
	public Res<PageInfo<User>> list(int pageNum) {
		PageHelper.startPage(pageNum, 10);
		List<User> userList = userMapper.selectAllUser();
		PageInfo<User> pageInfo = new PageInfo<>(userList);
		return Res.successData(pageInfo);
	}

	@Override
	public Res add(User user, Integer roleId) {
		//todo 密码MD5加密
		try{
			userMapper.insert(user);
			// 添加用户角色关系
			if(roleId != null)
				iUserRoleService.add(user.getId(), roleId);
		}catch (Exception e) {
			throw new BaseException("用户添加失败", e);
		}
		return Res.successMsg("用户添加成功");
	}

	@Override
	public Res edit(User user) {
		user.setUsername(null);
		user.setPassword(null);
		try{
			userMapper.updateByPrimaryKeySelective(user);
		}catch (Exception e) {
			throw new BaseException("用户修改失败", e);
		}
		return Res.successMsg("用户修改成功");
	}

	@Override
	@Transactional(readOnly=true)
	public Res info(Integer userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return Res.successData(user);
	}

	@Override
	public Res delete(Integer[] userIdArray) {
		try{
			// 批量删除用户
			userMapper.batchDeleteByUserId(userIdArray);
			
			// 批量删除用户角色关系
			for (Integer userId : userIdArray) {
				iUserRoleService.delete(userId);
			}
			return Res.successMsg("用户删除成功");
		}catch (Exception e) {
			throw new BaseException("用户删除失败", e);
		}
	}

	@Override
	public Res changePassword(Integer userId, String newPassword) {
		try {
			User user = new User();
			user.setId(userId);
			user.setPassword(newPassword);
			userMapper.updateByPrimaryKeySelective(user);
			return Res.successMsg("用户修改密码成功");
		} catch (Exception e) {
			throw new BaseException("用户修改密码失败", e);
		}
	}

	@Override
	public Res logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return Res.successMsg("登出成功");
	}

}
