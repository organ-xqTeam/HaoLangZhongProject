/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * sys_consultation_discussEntity
 * @author 范耘诚
 * @version 2019-03-28
 */
@Table(name="sys_consultation_discuss", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="content", attrName="content", label="内容"),
		@Column(name="consultation_id", attrName="consultationId", label="咨询表id主键"),
		@Column(name="user_id", attrName="userId", label="用户表主键"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="discuss_state", attrName="discussState", label="0患者  1医生"),
	},//支持联合查询，如左右连接查询，支持设置查询自定义关联表的返回字段列
	joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity=UserInfo.class, alias="u", 
			on="a.user_id = u.id",
			columns={@Column(includeEntity=UserInfo.class)}),
	}, orderBy="a.create_date asc"
)
public class ConsultationDiscuss extends DataEntity<ConsultationDiscuss> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 内容
	private String consultationId;		// 咨询表id主键
	private String userId;		// 用户表主键
	private String discussState;		// 0患者  1医生
	private UserInfo  userInfo;
	
	public ConsultationDiscuss() {
		this(null);
	}

	public ConsultationDiscuss(String id){
		super(id);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="咨询表id主键长度不能超过 64 个字符")
	public String getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
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