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
 * 药方表Entity
 * @author 范耘诚
 * @version 2019-03-15
 */
@Table(name="sys_air_prescription", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="content", attrName="content", label="药方内容"),
		@Column(name="pic1", attrName="pic1", label="图片1"),
		@Column(name="pic2", attrName="pic2", label="图片2"),
		@Column(name="pic3", attrName="pic3", label="图片3"),
		@Column(name="check_state", attrName="checkState", label="审核状态 0待审核, 1审核中, 2审核通过,-1审核失败"),
		@Column(name="user_id", attrName="userId", label="人员表的id"),
		@Column(name="pay_price", attrName="payPrice", label="审核通过后的价钱 ", comment="审核通过后的价钱 (字符串) 单位:分"),
		@Column(name="create_date", attrName="createDate", label="创建日期"),
		@Column(name="update_date", attrName="updateDate", label="更新日期"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="update_by", attrName="updateBy", label="添加人"),
		@Column(name="del_flag", attrName="delFlag", label="是否删除 0", comment="是否删除 0:未删除 ,1:删除"),
	}, orderBy="a.create_date DESC"
)
public class AirPrescription extends DataEntity<AirPrescription> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 药方内容
	private String pic1;		// 图片1
	private String pic2;		// 图片2
	private String pic3;		// 图片3
	private String checkState;		// 审核状态 0待审核, 1审核中, 2审核通过,-1审核失败
	private String userId;		// 人员表的id
	private String payPrice;		// 审核通过后的价钱 (字符串) 单位:分
	private String delFlag;		// 是否删除 0:未删除 ,1:删除
	
	public AirPrescription() {
		this(null);
	}

	public AirPrescription(String id){
		super(id);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	
	@Length(min=0, max=255, message="审核状态 0待审核, 1审核中, 2审核通过,-1审核失败长度不能超过 255 个字符")
	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min=0, max=10, message="审核通过后的价钱 长度不能超过 10 个字符")
	public String getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
	}
	
	@Length(min=0, max=1, message="是否删除 0长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}