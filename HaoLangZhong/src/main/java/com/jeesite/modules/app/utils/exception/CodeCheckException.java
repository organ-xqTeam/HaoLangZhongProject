package com.jeesite.modules.app.utils.exception;

@SuppressWarnings("serial")
public class CodeCheckException extends RuntimeException {
	public CodeCheckException() {}
	public CodeCheckException(String message) {
		super(message);
	}
	public void f() throws CodeCheckException {
	    throw new CodeCheckException("验证码不正确");
	} 
}
