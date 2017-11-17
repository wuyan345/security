package com.shiro.sys.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.sys.common.Res;
import com.shiro.sys.common.annotation.Log;
import com.shiro.sys.service.IUserService;

@RestController
@RequestMapping("/sys/user")
public class LoginController {

	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IUserService iUserService;
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/login")
	public Res login(String username, String password){
		logger.info("user: {}, password: {} is logging", username, password);
		return iUserService.login(username, password);
	}
	
	/**
	 * 用户登出
	 * @return
	 */
	@RequiresAuthentication
	@PostMapping("logout")
	public Res logout(){
		return iUserService.logout();
	}
}
