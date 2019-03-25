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
 * 药品与药品类别的多对多关系表Entity
 * @author 范耘诚
 * @version 2019-03-06
 */
@Table(name="sys_air_drug_relation_category", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="drug_id", attrName="drugId", label="药品表id"),
		@Column(name="category_id", attrName="categoryId", label="药品类别表id"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="是否删除"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新日期", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class AirDrugRelationCategory extends DataEntity<AirDrugRelationCategory> {
	
	private static final long serialVersionUID = 1L;
	private Long drugId;		// 药品表id
	private Long categoryId;		// 药品类别表id
	private String delFlag;		// 是否删除
	private Date createDate;  //创建日期
	private Date updateDate; //更新日期
	private String updateBy; //更新人
	private String createBy; //创建人
	private String remarks ; //备注
	
	public AirDrugRelationCategory() {
		this(null);
	}

	public AirDrugRelationCategory(String id){
		super(id);
	}
	
	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}