package com.shiro.sys.controller;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.sys.common.Res;
import com.shiro.sys.common.annotation.Log;
import com.shiro.sys.dao.UserMapper;
import com.shiro.sys.pojo.User;
import com.shiro.sys.service.IUserService;

@RestController
@RequestMapping("/sys/user")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService iUserService;
	
	@RequestMapping("/test")
	public Res<?> test(){
		System.out.println("进入UserController.test()");
		iUserService.test();
		return Res.successMsg("ok");
	}
	
	/**
	 * 显示用户列表
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@RequiresPermissions("sys:user:list")
	@Log("显示用户列表")
	public Res list(int pageNum){
		return iUserService.list(pageNum);
	}
	
	/**
	 * 添加用户，并分配角色
	 * @param user
	 * @return
	 */
	@RequiresPermissions("sys:user:add")
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@Log("添加用户")
	public Res add(User user, Integer roleId){
		return iUserService.add(user, roleId);
	}
	
	/**
	 * 用户信息修改
	 * @param user
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@Log("用户信息修改")
	public Res edit(User user){
		return iUserService.edit(user);
	}
	
	/**
	 * 显示当前用户的信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/info/{userId}", method=RequestMethod.POST)
	@Log("显示当前用户的信息")
	public Res info(@PathVariable Integer userId){
		// 防止横向越权
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		return iUserService.info(user.getId());
	}
	
	/**
	 * 删除1个或多个用户
	 * @param userId
	 * @return
	 */
	@RequiresPermissions("sys:user:delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@Log("删除用户")
	public Res delete(Integer[] userIdArray){
		return iUserService.delete(userIdArray);
	}
	
	/**
	 * 已登录状态下修改密码
	 * @param newPassword
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	@Log("修改密码")
	public Res changePassword(String newPassword){
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return iUserService.changePassword(user.getId(), newPassword);
	}
	
}
