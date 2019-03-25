package com.jeesite.modules.app.utils.exception;

@SuppressWarnings("serial")
public class PassCheckException extends RuntimeException {
	public PassCheckException() {}
	public PassCheckException(String message) {
		super(message);
	}
	public void f() throws PassCheckException {
	    throw new PassCheckException("确认密码不正确");
	} 
}
