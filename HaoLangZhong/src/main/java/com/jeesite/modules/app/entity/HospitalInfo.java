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
 * sys_hospital_infoEntity
 * @author 范耘诚
 * @version 2019-05-29
 */
@Table(name="sys_hospital_info", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="hospital_type", attrName="hospitalType", label="医院类型"),
		@Column(name="hospital_lv", attrName="hospitalLv", label="医院等级"),
		@Column(name="hospital_address", attrName="hospitalAddress", label="医院地址"),
		@Column(name="phone", attrName="phone", label="医院电话"),
		@Column(name="offices", attrName="offices", label="医院科室"),
		@Column(name="content", attrName="content", label="医院简介"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="del_flag", attrName="delFlag", label="del_flag"),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class HospitalInfo extends DataEntity<HospitalInfo> {
	
	private static final long serialVersionUID = 1L;
	private String hospitalType;		// 医院类型
	private String hospitalLv;		// 医院等级
	private String hospitalAddress;		// 医院地址
	private String phone;		// 医院电话
	private String offices;		// 医院科室
	private String content;		// 医院简介
	private String delFlag;		// del_flag
	
	public HospitalInfo() {
		this(null);
	}

	public HospitalInfo(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="医院类型长度不能超过 64 个字符")
	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}
	
	@Length(min=0, max=64, message="医院等级长度不能超过 64 个字符")
	public String getHospitalLv() {
		return hospitalLv;
	}

	public void setHospitalLv(String hospitalLv) {
		this.hospitalLv = hospitalLv;
	}
	
	@Length(min=0, max=64, message="医院地址长度不能超过 64 个字符")
	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}
	
	@Length(min=0, max=64, message="医院电话长度不能超过 64 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getOffices() {
		return offices;
	}

	public void setOffices(String offices) {
		this.offices = offices;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="del_flag长度不能超过 64 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}