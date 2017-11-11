package com.shiro.sys.common;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * @author WY
 *
 */
@RestControllerAdvice
public class ResExceptionHandler {

	private final static Logger logger = LoggerFactory.getLogger(ResExceptionHandler.class);
	
	@ExceptionHandler(AuthenticationException.class)
	public Res<?> handleAuthenticationException(AuthenticationException ae){
		logger.error(ae.getMessage(), ae);
		return Res.errorMsg("该用户未登录");
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public Res<?> handleAuthorizationException(AuthorizationException ae){
		logger.error(ae.getMessage(), ae);
		return Res.errorMsg("该用户没有权限");
	}
	
	@ExceptionHandler(ShiroException.class)
	public Res<?> handleShiroException(ShiroException se){
		logger.error(se.getMessage(), se);
		return Res.errorMsg(se.getMessage());
	}
	
	@ExceptionHandler(BaseException.class)
	public Res<?> handleBaseException(BaseException be){
		logger.error(be.getMessage(), be);
		return Res.errorMsg(be.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public Res<?> handleException(Exception e){
		logger.error(e.getMessage(), e);
		return Res.errorMsg("未知错误");
	}
}
