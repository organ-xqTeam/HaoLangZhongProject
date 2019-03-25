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
 * 购物车表Entity
 * @author 范耘诚
 * @version 2019-03-06
 */
@Table(name="sys_air_shop_cart", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="cart_code", attrName="cartCode", label="购物车编号"),
		@Column(name="total_price", attrName="totalPrice", label="总价钱"),
		@Column(name="cart_notice", attrName="cartNotice", label="购物车描述"),
		@Column(name="create_date", attrName="createDate", label="创建时间"),
		@Column(name="update_date", attrName="updateDate", label="更新时间"),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="update_by", attrName="updateBy", label="更新人"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="删除flag 0未删除 1删除"),
	}, orderBy="a.update_date DESC"
)
public class AirShopCart extends DataEntity<AirShopCart> {
	
	private static final long serialVersionUID = 1L;
	private String cartCode;		// 购物车编号
	private String totalPrice;		// 总价钱
	private String cartNotice;		// 购物车描述
	private String delFlag;		// 删除flag 0未删除 1删除
	private Date createDate;  //创建日期
	private Date updateDate; //更新日期
	private String updateBy; //更新人
	private String createBy; //创建人
	private String remarks ; //备注
	public AirShopCart() {
		this(null);
	}

	public AirShopCart(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="购物车编号长度不能超过 255 个字符")
	public String getCartCode() {
		return cartCode;
	}

	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}
	
	@Length(min=0, max=255, message="总价钱长度不能超过 255 个字符")
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Length(min=0, max=255, message="购物车描述长度不能超过 255 个字符")
	public String getCartNotice() {
		return cartNotice;
	}

	public void setCartNotice(String cartNotice) {
		this.cartNotice = cartNotice;
	}
	
	@Length(min=0, max=255, message="删除flag 0未删除 1删除长度不能超过 255 个字符")
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