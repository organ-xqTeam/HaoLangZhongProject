package com.jeesite.modules.app.utils.exception;

@SuppressWarnings("serial")
public class RedisCheckException extends RuntimeException {
	public RedisCheckException() {}
	public RedisCheckException(String message) {
		super(message);
	}
	public void f() throws RedisCheckException {
	    throw new RedisCheckException("用户未登录");
	} 
}