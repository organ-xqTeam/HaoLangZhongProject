package com.jeesite.modules.app.utils.exception;

@SuppressWarnings("serial")
public class NotExistException extends RuntimeException {
	public NotExistException() {}
	public NotExistException(String message) {
		super(message);
	}
	public void f() throws NotExistException {
	    throw new NotExistException("用户不存在");
	} 
}
