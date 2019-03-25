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
 * sys_air_drug_commentEntity
 * @author 范耘诚
 * @version 2019-03-06
 */
@Table(name="sys_air_drug_comment", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="content", attrName="content", label="点评内容"),
		@Column(name="star_grade", attrName="starGrade", label="星级 0-5星"),
		@Column(name="create_date", attrName="createDate", label="创建日期", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新日期", isQuery=false),
		@Column(name="is_flag", attrName="isFlag", label="是否删除 0未删除 1已删除"),
		@Column(name="user_id", attrName="userId", label="与用户表关联的id"),
		@Column(name="del_flag", attrName="delFlag", label="是否删除 0未删除 1已删除"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false),
		@Column(name="update_by", attrName="updateBy", label="更新人"),
		@Column(name="drug_id", attrName="drugId", label="与sys_air_drug 药品表的主键对应"),
	}, orderBy="a.update_date DESC"
)
public class AirDrugComment extends DataEntity<AirDrugComment> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 点评内容
	private String starGrade;		// 星级 0-5星
	private String isFlag;		// 是否删除 0未删除 1已删除
	private String userId;		// 与用户表关联的id
	private String delFlag;		// 是否删除 0未删除 1已删除
	private Long drugId;		// 与sys_air_drug 药品表的主键对应
	private Date createDate;  //创建日期
	private Date updateDate; //更新日期
	private String updateBy; //更新人
	private String createBy; //创建人
	private String remarks ; //备注
	public AirDrugComment() {
		this(null);
	}

	public AirDrugComment(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="点评内容长度不能超过 255 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="星级 0-5星长度不能超过 255 个字符")
	public String getStarGrade() {
		return starGrade;
	}

	public void setStarGrade(String starGrade) {
		this.starGrade = starGrade;
	}
	
	@Length(min=0, max=255, message="是否删除 0未删除 1已删除长度不能超过 255 个字符")
	public String getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min=0, max=1, message="是否删除 0未删除 1已删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
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