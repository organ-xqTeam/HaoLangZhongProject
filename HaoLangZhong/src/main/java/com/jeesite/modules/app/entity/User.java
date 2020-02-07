package com.jeesite.modules.app.entity;

public class User extends BaseEntity {

	// 手机号
	private String mobile;
	// 密码
	private String password;
	// 新密码
	private String newpass;
	// 确认密码
	private String confirm;
	// 验证码
	private String code;
	// 分组：1.普通用户，2.医生
	private String type;
	
	//邀请码
	private String shareCode;
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShareCode() {
		return shareCode;
	}
	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}
	
	
	
}
