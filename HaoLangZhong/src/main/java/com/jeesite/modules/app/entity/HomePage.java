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
 * sys_home_pageEntity
 * @author 范耘诚
 * @version 2019-04-15
 */
@Table(name="sys_home_page", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="picid", attrName="picid", label="图片的id"),
		@Column(name="skip_url", attrName="skipUrl", label="跳转的url"),
		@Column(name="create_date", attrName="createDate", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新人", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人信息", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="是否删除"),
	}, orderBy="a.update_date DESC"
)
public class HomePage extends DataEntity<HomePage> {
	
	private static final long serialVersionUID = 1L;
	private String picid;		// 图片的id
	private String skipUrl;		// 跳转的url
	private String delFlag;		// 是否删除
	
	public HomePage() {
		this(null);
	}

	public HomePage(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="图片的id长度不能超过 64 个字符")
	public String getPicid() {
		return picid;
	}

	public void setPicid(String picid) {
		this.picid = picid;
	}
	
	@Length(min=0, max=64, message="跳转的url长度不能超过 64 个字符")
	public String getSkipUrl() {
		return skipUrl;
	}

	public void setSkipUrl(String skipUrl) {
		this.skipUrl = skipUrl;
	}
	
	@Length(min=0, max=64, message="是否删除长度不能超过 64 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}