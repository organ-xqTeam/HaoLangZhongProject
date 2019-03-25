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
 * 订单向平详情表Entity
 * @author 范耘诚
 * @version 2019-03-13
 */
@Table(name="sys_order_detail", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="order_code", attrName="orderCode", label="订单编号"),
		@Column(name="grud_id", attrName="grudId", label="药品id", comment="药品id(二选一)"),
		@Column(name="prescription_id", attrName="prescriptionId", label="药方id", comment="药方id(二选一)"),
		@Column(name="product_name", attrName="productName", label="商品名称", queryType=QueryType.LIKE),
		@Column(name="product_price", attrName="productPrice", label="商品价格", comment="商品价格(没打折的时候)"),
		@Column(name="discount_rate", attrName="discountRate", label="折扣比例", comment="折扣比例(打几折)"),
		@Column(name="only_price", attrName="onlyPrice", label="单个商品打完折后的价格"),
		@Column(name="buy_count", attrName="buyCount", label="购买数量"),
		@Column(name="subtotal_price", attrName="subtotalPrice", label="商品小计"),
		@Column(name="invalid_flag", attrName="invalidFlag", label="是否失效  0", comment="是否失效  0:不失效,1:失效"),
		@Column(name="remarks", attrName="remarks", label="客户商品备注", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="是否删除 0 不删除 1删除"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="order_id", attrName="orderId", label="与订单表主键对应"),
	}, 	
	// 支持联合查询，如左右连接查询，支持设置查询自定义关联表的返回字段列
	joinTable={
		@JoinTable(type=Type.JOIN, entity=AirDrug.class, alias="sad", 
			on="a.grud_id = sad.id",
			columns={@Column(name="pic1", attrName="pic1", label="图片1")}),
	},orderBy="a.update_date DESC"
)
public class OrderDetail extends DataEntity<OrderDetail> {
	
	private static final long serialVersionUID = 1L;
	private String orderCode;		// 订单编号
	private Long grudId;		// 药品id(二选一)
	private Long prescriptionId;		// 药方id(二选一)
	private String productName;		// 商品名称
	private String productPrice;		// 商品价格(没打折的时候)
	private String discountRate;		// 折扣比例(打几折)
	private String onlyPrice;		// 单个商品打完折后的价格
	private String buyCount;		// 购买数量
	private String subtotalPrice;		// 商品小计
	private String invalidFlag;		// 是否失效  0:不失效,1:失效
	private String delFlag;		// 是否删除 0 不删除 1删除
	private Long orderId;		// 与订单表主键对应
	private String dpic1;
	private String dpic2;
	private String dpic3;
	private AirDrug airDrug;
	
	
	
	
	
	
	public AirDrug getAirDrug() {
		return airDrug;
	}

	public void setAirDrug(AirDrug airDrug) {
		this.airDrug = airDrug;
	}

	public OrderDetail() {
		this(null);
	}

	public OrderDetail(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="订单编号长度不能超过 64 个字符")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public Long getGrudId() {
		return grudId;
	}

	public void setGrudId(Long grudId) {
		this.grudId = grudId;
	}
	
	public Long getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=0, max=64, message="商品价格长度不能超过 64 个字符")
	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	@Length(min=0, max=64, message="折扣比例长度不能超过 64 个字符")
	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	
	@Length(min=0, max=64, message="单个商品打完折后的价格长度不能超过 64 个字符")
	public String getOnlyPrice() {
		return onlyPrice;
	}

	public void setOnlyPrice(String onlyPrice) {
		this.onlyPrice = onlyPrice;
	}
	
	@Length(min=0, max=64, message="购买数量长度不能超过 64 个字符")
	public String getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(String buyCount) {
		this.buyCount = buyCount;
	}
	
	@Length(min=0, max=64, message="商品小计长度不能超过 64 个字符")
	public String getSubtotalPrice() {
		return subtotalPrice;
	}

	public void setSubtotalPrice(String subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}
	
	@Length(min=0, max=1, message="是否失效  0长度不能超过 1 个字符")
	public String getInvalidFlag() {
		return invalidFlag;
	}

	public void setInvalidFlag(String invalidFlag) {
		this.invalidFlag = invalidFlag;
	}
	
	@Length(min=0, max=1, message="是否删除 0 不删除 1删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getDpic1() {
		return dpic1;
	}

	public void setDpic1(String dpic1) {
		this.dpic1 = dpic1;
	}

	public String getDpic2() {
		return dpic2;
	}

	public void setDpic2(String dpic2) {
		this.dpic2 = dpic2;
	}

	public String getDpic3() {
		return dpic3;
	}

	public void setDpic3(String dpic3) {
		this.dpic3 = dpic3;
	}
	
	
}