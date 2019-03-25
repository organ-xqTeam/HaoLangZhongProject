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
 * 用户分享表Entity
 * @author 范耘诚
 * @version 2019-03-15
 */
@Table(name="sys_user_share", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="share_code", attrName="shareCode", label="分享码"),
		@Column(name="user_id", attrName="userId", label="用户id"),
		@Column(name="is_use", attrName="isUse", label="是否已经使用0未使用 1已使用"),
		@Column(name="share_price", attrName="sharePrice", label="分享金额"),
		@Column(name="create_date", attrName="createDate", label="创建日期", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新日期", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
		@Column(name="del_flag", attrName="delFlag", label="是否删除 0未删除 1删除"),
	}, orderBy="a.update_date DESC"
)
public class UserShare extends DataEntity<UserShare> {
	
	private static final long serialVersionUID = 1L;
	private String shareCode;		// 分享码
	private String userId;		// 用户id
	private String isUse;		// 是否已经使用0未使用 1已使用
	private String sharePrice;		// 分享金额
	private String delFlag;		// 是否删除 0未删除 1删除
	
	public UserShare() {
		this(null);
	}

	public UserShare(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="分享码长度不能超过 64 个字符")
	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}
	
	@Length(min=0, max=64, message="用户id长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=1, message="是否已经使用0未使用 1已使用长度不能超过 1 个字符")
	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	
	@Length(min=0, max=64, message="分享金额长度不能超过 64 个字符")
	public String getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(String sharePrice) {
		this.sharePrice = sharePrice;
	}
	
	@Length(min=0, max=1, message="是否删除 0未删除 1删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}