/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * sys_discountEntity
 * @author 范耘诚
 * @version 2019-03-15
 */
@Table(name="sys_discount", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="user_id", attrName="userId", label="与用户主键相关联"),
		@Column(name="discount_price", attrName="discountPrice", label="折扣金额", comment="折扣金额(单位:分)"),
		@Column(name="create_date", attrName="createDate", label="创建日期", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
	},// 支持联合查询，如左右连接查询，支持设置查询自定义关联表的返回字段列
	joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity=DiscountDetail.class, alias="dd", 
			on="a.id = dd.discount_id",
			columns={@Column(includeEntity=DiscountDetail.class)}),
	}, orderBy="a.update_date DESC"
)
public class Discount extends DataEntity<Discount> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 与用户主键相关联
	private String discountPrice;		// 折扣金额(单位:分)
	private DiscountDetail discountDetail;
	private String id;
	
	public Discount() {
		this(null);
	}

	public Discount(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="与用户主键相关联长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=20, message="折扣金额长度不能超过 20 个字符")
	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public DiscountDetail getDiscountDetail() {
		return discountDetail;
	}

	public void setDiscountDetail(DiscountDetail discountDetail) {
		this.discountDetail = discountDetail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id==null) {
			this.id=null;
			super.id = null;
		}else {
			this.id=id;
			super.id = id;
		}
	}
	
	
}