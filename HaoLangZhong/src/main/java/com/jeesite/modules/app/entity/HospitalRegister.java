/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * sys_hospital_registerEntity
 * @author 范耘诚
 * @version 2019-05-29
 *  预约表
 */
@Table(name="sys_hospital_register", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="姓名", queryType=QueryType.LIKE),
		@Column(name="phone", attrName="phone", label="电话"),
		@Column(name="gender", attrName="gender", label="性别"),
		@Column(name="gender_type", attrName="genderType", label="0不明 1男 2女"),
		@Column(name="age", attrName="age", label="年龄"),
		@Column(name="make_date", attrName="makeDate", label="预约时间"),
		@Column(name="content", attrName="content", label="症状描述"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="user_id", attrName="userId", label="user_id"),
	}, orderBy="a.update_date DESC"
)
public class HospitalRegister extends DataEntity<HospitalRegister> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String phone;		// 电话
	private String gender;		// 性别
	private String genderType;		// 0不明 1男 2女
	private String age;		// 年龄
	private Date makeDate;		// 预约时间
	private String content;		// 症状描述
	private String userId;		// user_id
	
	
	private String makeDates; //前台形式的makeDates
	public HospitalRegister() {
		this(null);
	}

	public HospitalRegister(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="姓名长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="电话长度不能超过 64 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=64, message="性别长度不能超过 64 个字符")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=64, message="0不明 1男 2女长度不能超过 64 个字符")
	public String getGenderType() {
		return genderType;
	}

	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}
	
	@Length(min=0, max=64, message="年龄长度不能超过 64 个字符")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}
	
	@Length(min=0, max=64, message="症状描述长度不能超过 64 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="user_id长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMakeDates() {
		return makeDates;
	}

	public void setMakeDates(String makeDates) {
		this.makeDates = makeDates;
	}
	
}