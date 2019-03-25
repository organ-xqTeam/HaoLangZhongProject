/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.entity;


import java.util.Date;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 购物车关系表Entity
 * @author 范耘诚
 * @version 2019-03-06
 */
@Table(name="sys_air_shop_cart_relation", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="cart_id", attrName="cartId", label="购物车主键"),
		@Column(name="drug_id", attrName="drugId", label="商品主键"),
		@Column(name="user_id", attrName="userId", label="与用户表主键"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class AirShopCartRelation extends DataEntity<AirShopCartRelation> {
	
	private static final long serialVersionUID = 1L;
	private Long cartId;		// 购物车主键
	private Long drugId;		// 商品主键
	private Long userId;		// 与用户表主键
	private Date createDate;  //创建日期
	private Date updateDate; //更新日期
	private String updateBy; //更新人
	private String createBy; //创建人
	private String remarks ; //备注
	public AirShopCartRelation() {
		this(null);
	}

	public AirShopCartRelation(String id){
		super(id);
	}
	
	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	
	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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