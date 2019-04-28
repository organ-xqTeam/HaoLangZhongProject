/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * sys_publicEntity
 * @author 范耘诚
 * @version 2019-04-24
 */
@Table(name="sys_public", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="num", attrName="num", label="咨询编号"),
		@Column(name="body", attrName="body", label="身体部位"),
		@Column(name="therapy", attrName="therapy", label="接受疗法"),
		@Column(name="content", attrName="content", label="咨询内容"),
		@Column(name="reply", attrName="reply", label="专家回复"),
		@Column(name="replytime", attrName="replytime", label="回复时间"),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="del_flag"),
		@Column(name="user_id", attrName="userId", label="用户的主键id"),
		@Column(name="disease", attrName="disease", label="病症id"),
		@Column(name="review_flag", attrName="reviewFlag", label="是否审核成功 0 审核不通过  1审核通过"),
	}, orderBy="a.update_date DESC"
)
public class Public extends DataEntity<Public> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 咨询编号
	private String body;		// 身体部位
	private String therapy;		// 接受疗法
	private String content;		// 咨询内容
	private String reply;		// 专家回复
	private String replytime;		// 回复时间
	private String delFlag;		// del_flag
	private String userId;		// 用户的主键id
	private String disease;		// 病症id
	private String reviewFlag;		// 是否审核成功 0 审核不通过  1审核通过
	
	//用户表的icon
	private String userIcon;
	
	public Public() {
		this(null);
	}

	public Public(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="咨询编号长度不能超过 64 个字符")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=64, message="身体部位长度不能超过 64 个字符")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	@Length(min=0, max=64, message="接受疗法长度不能超过 64 个字符")
	public String getTherapy() {
		return therapy;
	}

	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	
	@Length(min=0, max=64, message="回复时间长度不能超过 64 个字符")
	public String getReplytime() {
		return replytime;
	}

	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}
	
	@Length(min=0, max=1, message="del_flag长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Length(min=0, max=64, message="用户的主键id长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=64, message="病症id长度不能超过 64 个字符")
	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	@Length(min=0, max=1, message="是否审核成功 0 审核不通过  1审核通过长度不能超过 1 个字符")
	public String getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	
	
}