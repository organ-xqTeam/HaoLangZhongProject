/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * sys_user_infoEntity
 * @author 范耘诚
 * @version 2019-03-28
 */
@Table(name="sys_user_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="mobile", attrName="mobile", label="手机号"),
		@Column(name="password", attrName="password", label="密码"),
		@Column(name="icon", attrName="icon", label="头像"),
		@Column(name="regtime", attrName="regtime", label="注册时间"),
		@Column(name="lastlogin", attrName="lastlogin", label="最后登录时间"),
		@Column(name="islock", attrName="islock", label="是否被锁定", comment="是否被锁定：1.未锁定，0.已锁定"),
		@Column(name="isauthentication", attrName="isauthentication", label="是否完成认证", comment="是否完成认证：1.未认证，0.已认证，2.未通过"),
		@Column(name="type", attrName="type", label="类型", comment="类型：1.普通用户，2.医生"),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="del_flag"),
		@Column(name="nike_name", attrName="nikeName", label="昵称", queryType=QueryType.LIKE),
		@Column(name="is_invite", attrName="isInvite", label="是否是被邀请的用户 0不是被邀请用户 ,1是被邀请的用户"), 
		@Column(name="third_id", attrName="thirdId", label="第三方的id"), 
		@Column(name="third_type", attrName="thirdType", label="第三方类型 0无第三方 1:微信 2:qq"), 
		@Column(name="third_icon", attrName="thirdIcon", label="第三方头像上传的id"), 
		@Column(name="member_lv", attrName="memberLv", label="用户等级"), 
		@Column(name="member_flag", attrName="memberFlag", label="是否为会员"), 
		@Column(name="member_start", attrName="memberStart", label="会员开始时间"), 
		@Column(name="member_end", attrName="memberEnd", label="会员结束时间"), 
	}, orderBy="a.update_date DESC"
)
public class UserInfo extends DataEntity<UserInfo> {
	
	private static final long serialVersionUID = 1L;
	private String mobile;		// 手机号
	private String password;		// 密码
	private String icon;		// 头像
	private String regtime;		// 注册时间
	private String lastlogin;		// 最后登录时间
	private String islock;		// 是否被锁定：1.未锁定，0.已锁定
	private String isauthentication;		// 是否完成认证：1.未认证，0.已认证，2.未通过
	private String type;		// 类型：1.普通用户，2.医生
	private String delFlag;		// del_flag
	private String nikeName;		// 昵称
	private String isInvite;		// 是否是被邀请的用户 0不是被邀请用户 ,1是被邀请的用户
	
	private String thirdId;    //第三方的id
	private String thirdType; //第三方类型 0无第三方 1:微信 2:qq
	private String thirdIcon; //第三方头像上传的id
	private String memberLv;   //用户的会员等级
	private String memberFlag;   //是否为会员
	private Date memberStart;   //是否为会员
	private Date memberEnd;   //是否为会员
	public UserInfo() {
		this(null);
	}

	public UserInfo(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="手机号长度不能超过 64 个字符")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=64, message="密码长度不能超过 64 个字符")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=64, message="头像长度不能超过 64 个字符")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(min=0, max=64, message="注册时间长度不能超过 64 个字符")
	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	@Length(min=0, max=64, message="最后登录时间长度不能超过 64 个字符")
	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	
	@Length(min=0, max=64, message="是否被锁定长度不能超过 64 个字符")
	public String getIslock() {
		return islock;
	}

	public void setIslock(String islock) {
		this.islock = islock;
	}
	
	@Length(min=0, max=64, message="是否完成认证长度不能超过 64 个字符")
	public String getIsauthentication() {
		return isauthentication;
	}

	public void setIsauthentication(String isauthentication) {
		this.isauthentication = isauthentication;
	}
	
	@Length(min=0, max=64, message="类型长度不能超过 64 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=1, message="del_flag长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Length(min=0, max=64, message="昵称长度不能超过 64 个字符")
	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	
	@Length(min=0, max=1, message="是否是被邀请的用户 0不是被邀请用户 ,1是被邀请的用户长度不能超过 1 个字符")
	public String getIsInvite() {
		return isInvite;
	}

	public void setIsInvite(String isInvite) {
		this.isInvite = isInvite;
	}
	@Length(min=0, max=64, message="昵称长度不能超过 64 个字符")
	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}
	@Length(min=0, max=64, message="昵称长度不能超过 64 个字符")
	public String getThirdType() {
		return thirdType;
	}

	public void setThirdType(String thirdType) {
		this.thirdType = thirdType;
	}
	@Length(min=0, max=64, message="昵称长度不能超过 64 个字符")
	public String getThirdIcon() {
		return thirdIcon;
	}

	public void setThirdIcon(String thirdIcon) {
		this.thirdIcon = thirdIcon;
	}

	public String getMemberLv() {
		return memberLv;
	}
	@Length(min=0, max=64, message="昵称长度不能超过 64 个字符")
	public void setMemberLv(String memberLv) {
		this.memberLv = memberLv;
	}

	public String getMemberFlag() {
		return memberFlag;
	}

	public void setMemberFlag(String memberFlag) {
		this.memberFlag = memberFlag;
	}

	public Date getMemberStart() {
		return memberStart;
	}

	public void setMemberStart(Date memberStart) {
		this.memberStart = memberStart;
	}

	public Date getMemberEnd() {
		return memberEnd;
	}

	public void setMemberEnd(Date memberEnd) {
		this.memberEnd = memberEnd;
	}
	
	
	
	
}