package com.jeesite.modules.app.utils.exception;

@SuppressWarnings("serial")
public class MobileRepeatException extends RuntimeException {
	public MobileRepeatException() {}
	public MobileRepeatException(String message) {
		super(message);
	}
	public void f() throws MobileRepeatException {
	    throw new MobileRepeatException("手机号已被注册过");
	} 
}
