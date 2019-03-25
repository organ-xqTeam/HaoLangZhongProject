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
 * sys_air_drugEntity
 * @author 范耘诚
 * @version 2019-03-06
 */
@Table(name="sys_air_drug", alias="sad", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="药品名称", queryType=QueryType.LIKE),
		@Column(name="create_date", attrName="createDate", label="创建日期"),
		@Column(name="memo", attrName="memo", label="备注详情"),
		@Column(name="pay_price", attrName="payPrice", label="购买价格", comment="购买价格(字符串)单位:分"),
		@Column(name="pic1", attrName="pic1", label="图片1"),
		@Column(name="pic2", attrName="pic2", label="图片2"),
		@Column(name="pic3", attrName="pic3", label="图片3"),
		@Column(name="drug_inventory_id", attrName="drugInventoryId", label="与sys_air_drug_comment关联的id", comment="与sys_air_drug_comment关联的id(库存id)"),
		@Column(name="is_flag", attrName="isFlag", label="是否删除 0", comment="是否删除 0:不删除,1:删除"),
		@Column(name="first_flag", attrName="firstFlag", label="是否优先显示在推荐中 0", comment="是否优先显示在推荐中 0:不优先显示,1优先显示"),
		@Column(name="update_date", attrName="updateDate", label="更新时间"),
		@Column(name="del_flag", attrName="delFlag", label="是否删除 0", comment="是否删除 0:不删除,1:删除"),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="update_by", attrName="updateBy", label="更新人"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class AirDrug extends DataEntity<AirDrug> {
	
	private static final long serialVersionUID = 1L;

	private String name;		// 药品名称
	private String memo;		// 备注详情
	private String payPrice;		// 购买价格(字符串)单位:分
	private String pic1;		// 图片1
	private String pic2;		// 图片2
	private String pic3;		// 图片3
	private Long drugInventoryId;		// 与sys_air_drug_comment关联的id(库存id)
	private String isFlag;		// 是否删除 0:不删除,1:删除
	private String firstFlag;		// 是否优先显示在推荐中 0:不优先显示,1优先显示
	private String delFlag;		// 是否删除 0:不删除,1:删除
	private Date createDate;  //创建日期
	private Date updateDate; //更新日期
	private String updateBy; //更新人
	private String createBy; //创建人
	private String remarks ; //备注
	private String drugCode; //审批编号
	private String drugLnd; //适应症
	private String limit; //适应症
	

	
	public AirDrug() {
		this(null);
	}

	public AirDrug(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="药品名称长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="备注详情长度不能超过 255 个字符")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=10, message="购买价格长度不能超过 10 个字符")
	public String getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
	}
	
	@Length(min=0, max=255, message="图片1长度不能超过 255 个字符")
	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	
	@Length(min=0, max=255, message="图片2长度不能超过 255 个字符")
	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	
	@Length(min=0, max=255, message="图片3长度不能超过 255 个字符")
	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	

	
	public Long getDrugInventoryId() {
		return drugInventoryId;
	}

	public void setDrugInventoryId(Long drugInventoryId) {
		this.drugInventoryId = drugInventoryId;
	}
	
	@Length(min=0, max=255, message="是否删除 0长度不能超过 255 个字符")
	public String getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}
	
	@Length(min=0, max=255, message="是否优先显示在推荐中 0长度不能超过 255 个字符")
	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}
	
	@Length(min=0, max=1, message="是否删除 0长度不能超过 1 个字符")
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

	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getDrugLnd() {
		return drugLnd;
	}

	public void setDrugLnd(String drugLnd) {
		this.drugLnd = drugLnd;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	
	
	
}