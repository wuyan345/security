package com.shiro.sys.common;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 返回前端的数据包装
 * @author WY
 *
 * @param <T>
 */
public class Res <T> extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	
	public Res(int code){
		super();
		super.put("code", code);
	}
	public Res(int code, String msg){
		super();
		super.put("code", code);
		super.put("msg", msg);
	}
	public Res(int code, T data){
		super();
		super.put("code", code);
		super.put("msg", data);
	}
	public Res(int code, String msg, T data){
		super();
		super.put("code", code);
		super.put("msg", msg);
		super.put("data", data);
	}
	
	@JsonIgnore
	public boolean isSuccess(){
		int code = (int) super.get("code");
		return code == Const.RES.SUCCESS ? true : false;
	}
	
	public static Res <?> success(){
		return new Res<>(Const.RES.SUCCESS);
	}
	public static Res <?> successMsg(String msg){
		return new Res<>(Const.RES.SUCCESS, msg);
	}
	public static <T> Res <T> successData(T data){
		return new Res<>(Const.RES.SUCCESS, data);
	}
	public static <T> Res <T> successMsgData(String msg, T data){
		return new Res<>(Const.RES.SUCCESS, msg, data);
	}
	
	public static Res <?> error(){
		return new Res<>(Const.RES.ERROR);
	}
	public static Res <?> errorMsg(String msg){
		return new Res<>(Const.RES.ERROR, msg);
	}
	public static <T> Res <T> errorMsgData(String msg, T data){
		return new Res<>(Const.RES.ERROR, msg, data);
	}
	
}
