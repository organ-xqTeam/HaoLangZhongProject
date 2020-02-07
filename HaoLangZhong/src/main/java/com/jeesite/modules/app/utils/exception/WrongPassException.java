package com.jeesite.modules.app.utils.exception;

@SuppressWarnings("serial")
public class WrongPassException extends RuntimeException {
	public WrongPassException() {}
	public WrongPassException(String message) {
		super(message);
	}
	public void f() throws WrongPassException {
	    throw new WrongPassException("密码错误");
	} 
}
