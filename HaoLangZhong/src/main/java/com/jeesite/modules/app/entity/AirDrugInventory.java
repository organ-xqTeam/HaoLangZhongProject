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
 * sys_air_drug_inventoryEntity
 * @author 范耘诚
 * @version 2019-03-06
 */
@Table(name="sys_air_drug_inventory", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="drug_count", attrName="drugCount", label="库存数量"),
		@Column(name="shortage_flag", attrName="shortageFlag", label="是否缺货 0", comment="是否缺货 0:缺货 ,1:不缺货"),
		@Column(name="create_date", attrName="createDate", label="创建日期"),
		@Column(name="del_flag", attrName="delFlag", label="是否删除"),
		@Column(name="update_date", attrName="updateDate", label="更新日期"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="update_by", attrName="updateBy", label="更新人"),
	}, orderBy="a.update_date DESC"
)
public class AirDrugInventory extends DataEntity<AirDrugInventory> {
	
	private static final long serialVersionUID = 1L;
	private String drugCount;		// 库存数量
	private String shortageFlag;		// 是否缺货 0:缺货 ,1:不缺货
	private String delFlag;		// 是否删除
	private Date createDate;  //创建日期
	private Date updateDate; //更新日期
	private String updateBy; //更新人
	private String createBy; //创建人
	private String remarks ; //备注
	
	public AirDrugInventory() {
		this(null);
	}

	public AirDrugInventory(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="库存数量长度不能超过 255 个字符")
	public String getDrugCount() {
		return drugCount;
	}

	public void setDrugCount(String drugCount) {
		this.drugCount = drugCount;
	}
	
	@Length(min=0, max=255, message="是否缺货 0长度不能超过 255 个字符")
	public String getShortageFlag() {
		return shortageFlag;
	}

	public void setShortageFlag(String shortageFlag) {
		this.shortageFlag = shortageFlag;
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