package com.jeesite.modules.app.utils.exception;

@SuppressWarnings("serial")
public class NotLoginException extends RuntimeException {
	public NotLoginException() {}
	public NotLoginException(String message) {
		super(message);
	}
	public void f() throws NotLoginException {
	    throw new NotLoginException("用户未登录");
	}
}
