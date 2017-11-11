package com.shiro.sys.common;

/**
 * 自定义异常处理
 * @author WY
 *
 */
public class BaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BaseException(){
		super();
	}
	
	public BaseException(String msg){
		super(msg);
	}
	
	public BaseException(String msg, Throwable e){
		super(msg, e);
	}
}
