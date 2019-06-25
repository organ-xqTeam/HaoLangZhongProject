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
 * sys_app_versionEntity
 * @author 范耘诚
 * @version 2019-05-29
 */
@Table(name="sys_app_version", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="version", attrName="version", label="版本"),
		@Column(name="type", attrName="type", label="类型   0 安卓   1苹果"),
		@Column(name="url", attrName="url", label="下载地址"),
		@Column(name="must_flag", attrName="mustFlag", label="0 不必须  1必须"),
	}, orderBy="a.id DESC"
)
public class AppVersion extends DataEntity<AppVersion> {
	
	private static final long serialVersionUID = 1L;
	private String version;		// 版本
	private String type;		// 类型   0 安卓   1苹果
	private String url;		// 下载地址
	private String mustFlag;
	public AppVersion() {
		this(null);
	}

	public AppVersion(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="版本长度不能超过 64 个字符")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=0, max=64, message="类型   0 安卓   1苹果长度不能超过 64 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="下载地址长度不能超过 64 个字符")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMustFlag() {
		return mustFlag;
	}
	@Length(min=0, max=64, message="0 不必须  1必须")
	public void setMustFlag(String mustFlag) {
		this.mustFlag = mustFlag;
	}
	
	
}