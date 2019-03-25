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
 * 收货地址表Entity
 * @author 范耘诚
 * @version 2019-03-11
 */
@Table(name="sys_address", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="user_id", attrName="userId", label="与用户表主键对应"),
		@Column(name="real_name", attrName="realName", label="收货人姓名", queryType=QueryType.LIKE),
		@Column(name="phone", attrName="phone", label="电话"),
		@Column(name="phone2", attrName="phone2", label="电话2"),
		@Column(name="province", attrName="province", label="省份"),
		@Column(name="city", attrName="city", label="城市"),
		@Column(name="area", attrName="area", label="地区"),
		@Column(name="street", attrName="street", label="详细地址"),
		@Column(name="zip", attrName="zip", label="邮政编码"),
		@Column(name="is_default", attrName="isDefault", label="是否是默认地址 0不是,1是"),
		@Column(name="create_date", attrName="createDate", label="创建时间"),
		@Column(name="update_date", attrName="updateDate", label="更新时间"),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="update_by", attrName="updateBy", label="更新人"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="是否删除 0未删除 1已删除"),
	}, orderBy="a.update_date DESC"
)
public class Address extends DataEntity<Address> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 与用户表主键对应
	private String realName;	// 收货人姓名
	private String phone;		// 电话
	private String phone2;		// 电话2
	private String province;	// 省份
	private String city;		// 城市
	private String area;		// 地区
	private String street;		// 详细地址
	private String zip;		// 邮政编码
	private String isDefault;		// 是否是默认地址 0不是,1是
	private String delFlag;		// 是否删除 0未删除 1已删除
	
	public Address() {
		this(null);
	}

	public Address(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="与用户表主键对应长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=64, message="收货人姓名长度不能超过 64 个字符")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Length(min=0, max=64, message="电话长度不能超过 64 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=64, message="电话2长度不能超过 64 个字符")
	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	@Length(min=0, max=64, message="省份长度不能超过 64 个字符")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=64, message="城市长度不能超过 64 个字符")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=64, message="地区长度不能超过 64 个字符")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=64, message="详细地址长度不能超过 64 个字符")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	@Length(min=0, max=64, message="邮政编码长度不能超过 64 个字符")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Length(min=0, max=1, message="是否是默认地址 0不是,1是长度不能超过 1 个字符")
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	@Length(min=0, max=1, message="是否删除 0未删除 1已删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}