package com.jeesite.modules.app.entity;

public class UserCollection extends BaseEntity {

	private String userid;
	
	private String type;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
