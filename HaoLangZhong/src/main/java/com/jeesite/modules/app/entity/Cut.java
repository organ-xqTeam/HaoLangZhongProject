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
 * 抽成表Entity
 * @author 范耘诚
 * @version 2019-04-04
 */
@Table(name="sys_cut", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="cut", attrName="cut", label="抽成", comment="用户得到的提成(0.xxx)"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class Cut extends DataEntity<Cut> {
	
	private static final long serialVersionUID = 1L;
	private String cut;		// 用户得到的提成(0.xxx)
	
	public Cut() {
		this(null);
	}

	public Cut(String id){
		super(id);
	}
	
	@Length(min=0, max=20, message="抽成长度不能超过 20 个字符")
	public String getCut() {
		return cut;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}
	
}