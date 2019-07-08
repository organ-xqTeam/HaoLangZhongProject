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
 * 药品标签表Entity
 * @author 范耘诚
 * @version 2019-07-08
 */
@Table(name="sys_air_drug_label", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="标签名字", queryType=QueryType.LIKE),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="是否删除 0未删除 1删除"),
	}, orderBy="a.update_date DESC"
)
public class AirDrugLabel extends DataEntity<AirDrugLabel> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 标签名字
	private String delFlag;		// 是否删除 0未删除 1删除
	
	public AirDrugLabel() {
		this(null);
	}

	public AirDrugLabel(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="标签名字长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="是否删除 0未删除 1删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}