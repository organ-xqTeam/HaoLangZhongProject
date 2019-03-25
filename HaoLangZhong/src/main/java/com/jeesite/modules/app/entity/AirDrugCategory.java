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
 * sys_air_drug_categoryEntity
 * @author 范耘诚
 * @version 2019-03-06
 */
@Table(name="sys_air_drug_category", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="中成药名称", queryType=QueryType.LIKE),
		@Column(name="create_date", attrName="createDate", label="创建日期", isUpdate=false),
		@Column(name="update_date", attrName="updateDate", label="更新日期"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="是否删除"),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="update_by", attrName="updateBy", label="更新人")
	}, orderBy="a.update_date DESC"
)
public class AirDrugCategory extends DataEntity<AirDrugCategory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 中成药名称
	private String delFlag;		// 是否删除
	private Date createDate;  //创建日期
	private Date updateDate; //更新日期
	private String updateBy; //更新人
	private String createBy; //创建人
	private String remarks ; //备注
	
	public AirDrugCategory() {
		this(null);
	}

	public AirDrugCategory(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="中成药名称长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="是否删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}