package com.shiro.sys.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message <T>{

	private int status;
	
	private String msg;
	
	private T data;

	private Message(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	@JsonIgnore
	public boolean isSuccess(){
		return status == Const.RES.SUCCESS ? true : false;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static <T> Message<T> successMsg(String msg){
		return new Message<>(Const.RES.SUCCESS, msg);
	}
	
	public static <T> Message<T> errorMsg(String msg){
		return new Message<>(Const.RES.ERROR, msg);
	}
}
