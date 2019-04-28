/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * sys_public_discussEntity
 * @author 范耘诚
 * @version 2019-04-24
 */
@Table(name="sys_public_discuss", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="content", attrName="content", label="内容"),
		@Column(name="public_id", attrName="publicId", label="免费咨询表id主键"),
		@Column(name="user_id", attrName="userId", label="用户表主键"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="discuss_state", attrName="discussState", label="0患者  1医生"),
	}, orderBy="a.create_date DESC"
)
public class PublicDiscuss extends DataEntity<PublicDiscuss> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 内容
	private String publicId;		// 咨询表id主键
	private String userId;		// 用户表主键
	private String discussState;		// 0患者  1医生
	private UserInfo  userInfo;
	
	
	public PublicDiscuss() {
		this(null);
	}

	public PublicDiscuss(String id){
		super(id);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	@Length(min=0, max=64, message="用户表主键长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@NotBlank(message="0患者  1医生不能为空")
	@Length(min=0, max=1, message="0患者  1医生长度不能超过 1 个字符")
	public String getDiscussState() {
		return discussState;
	}

	public void setDiscussState(String discussState) {
		this.discussState = discussState;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
}