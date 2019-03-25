package com.jeesite.modules.app.utils;

public class Result<T> {
	
	private T data;
	private int code;
	private String msg;
	
	private Result(T data) {
		this.code = 0;
		this.msg = "成功";
		this.data = data;
	}
	
	private Result(CodeMsg cm) {
		if(cm == null){
			return;
		}
		this.msg = cm.getMsg();
		this.code = cm.getCode();
	}
	
	/**
	 * 成功时候的调用
	 * @return
	 */
	public static <T> Result<T> success(T data){
		return new Result<T>(data);
	}
	
	/**
	 * 成功，不需要传入参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Result<T> success(){
		return (Result<T>) success("");
	}
	
	/**
	 * 失败时候的调用
	 * @return
	 */
	public static <T> Result<T> error(CodeMsg cm){
		return new Result<T>(cm);
	}
	
	/**
	 * 失败时候的调用,扩展消息参数
	 * @param cm
	 * @param msg
	 * @return
	 */
	public static <T> Result<T> error(CodeMsg cm,String msg){
		cm.setMsg(cm.getMsg()+"--"+msg);
		return new Result<T>(cm);
	}
	
	public T getData() {
		return data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
