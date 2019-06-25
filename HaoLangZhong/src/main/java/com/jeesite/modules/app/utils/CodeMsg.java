package com.jeesite.modules.app.utils;

public class CodeMsg {
	
	private int code;
	private String msg;
	
	// 按照模块定义CodeMsg
	public static CodeMsg SUCCESS = new CodeMsg(0,"success");
	public static CodeMsg SERVER_EXCEPTION = new CodeMsg(500100,"服务端异常");
	public static CodeMsg PARAMETER_ISNULL = new CodeMsg(500101,"输入参数异常");
	
	public static CodeMsg USER_NOT_EXSIST = new CodeMsg(500102,"用户不存在"); 
	public static CodeMsg ONLINE_USER_OVER = new CodeMsg(500103,"在线用户数超出允许登录的最大用户限制。"); 
	public static CodeMsg SESSION_NOT_EXSIST =  new CodeMsg(500104,"不存在离线session数据");
	public static CodeMsg NOT_FIND_DATA = new CodeMsg(500105,"查找不到对应数据");
	public static CodeMsg UPLOAD_FAIL1 = new CodeMsg(500106,"Unable to upload. File is empty.");
	public static CodeMsg UPLOAD_FAIL2 = new CodeMsg(500107,"保存异常");
	public static CodeMsg DOWNLOAD_FAIL = new CodeMsg(500108,"文件加载失败");
	public static CodeMsg UPLOAD_NO_PIC = new CodeMsg(500109,"保存的文件不是图片");	
	public static CodeMsg PRICE_FAIL = new CodeMsg(500110,"支队金额有误");	
	public static CodeMsg TOKEN_INVALID = new CodeMsg(400,"用户未登录");
	public static CodeMsg NOT_EXIST =  new CodeMsg(401,"用户不存在");
	public static CodeMsg ISAUTH_FALSE =  new CodeMsg(407,"用户未进行审核,跳转");
	public static CodeMsg ISAUTH_TRUE =  new CodeMsg(408,"审核中");
	public static CodeMsg JUMP_BIND =  new CodeMsg(409,"请跳转到手机绑定界面");
	public static CodeMsg WRONG_PASS =  new CodeMsg(402,"密码错误");
	public static CodeMsg NOT_LOGIN = new CodeMsg(403,"用户未登录");
	public static CodeMsg PASS_CHECK = new CodeMsg(404,"确认密码不正确");
	public static CodeMsg CODE_CHECK = new CodeMsg(405,"验证码不正确");
	public static CodeMsg MOBILE_REPEAT = new CodeMsg(406,"手机号已被注册过");
	public static CodeMsg CODE_EXCEPTION = new CodeMsg(500111,"验证码不正确");
	public static CodeMsg TRUE_THIRD_RE = new CodeMsg(200100,"第三方注册成功");
	public static CodeMsg TRUE_THIRD_BING = new CodeMsg(200101,"第三方绑定成功");
	
	private CodeMsg(int retCode, String message) {
		this.msg = message;
		this.code = retCode;
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
