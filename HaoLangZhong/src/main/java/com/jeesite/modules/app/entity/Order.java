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
 * sys_orderEntity
 * @author 范耘诚
 * @version 2019-03-14
 */
@Table(name="sys_order", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="order_no", attrName="orderNo", label="订单编号", comment="订单编号(本地自动生成)"),
		@Column(name="order_status", attrName="orderStatus", label="订单状态 0未付款"),
		@Column(name="product_count", attrName="productCount", label="商品类别数量"),
		@Column(name="total_price", attrName="totalPrice", label="商品总价", comment="商品总价(不打折扣的价格)(分)"),
		@Column(name="logistics_price", attrName="logisticsPrice", label="运费金额", comment="运费金额(分)"),
		@Column(name="discount_price", attrName="discountPrice", label="折扣价格", comment="折扣价格(分)"),
		@Column(name="really_price", attrName="reallyPrice", label="真实支付价格"),
		@Column(name="pay_channel", attrName="payChannel", label="支付类型", comment="支付类型(微信/支付宝/银联)"),
		@Column(name="out_trade_no", attrName="outTradeNo", label="第三方支付编号"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
		@Column(name="pay_date", attrName="payDate", label="付款时间"),
		@Column(name="user_id", attrName="userId", label="用户id"),
		@Column(name="address_id", attrName="addressId", label="与用户地址表主键对应"),
		@Column(name="logistics_id", attrName="logisticsId", label="与物流表主键对应"),
		@Column(name="delivery_date", attrName="deliveryDate", label="发货时间"),
		@Column(name="del_flag", attrName="delFlag", label="是否删除"),
		@Column(name="delivery_name", attrName="deliveryName", label="收货人姓名", queryType=QueryType.LIKE),
		@Column(name="delivery_address", attrName="deliveryAddress", label="到达地址"),
		@Column(name="delivery_phone", attrName="deliveryPhone", label="到达电话"),
		@Column(name="total_count", attrName="totalCount", label="商品总数"),
		@Column(name="trade_no", attrName="tradeNo", label="支付宝交易号支付宝专用"),
	},orderBy="a.update_date DESC"
)
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单编号(本地自动生成)
	private String orderStatus;		// 订单状态 0未付款
	private String productCount;		// 商品类别数量
	private String totalPrice;		// 商品总价(不打折扣的价格)(分)
	private String logisticsPrice;		// 运费金额(分)
	private String discountPrice;		// 折扣价格(分)
	private String reallyPrice;		// 真实支付价格
	private String payChannel;		// 支付类型(微信/支付宝/银联)
	private String outTradeNo;		// 第三方支付编号
	private Date payDate;		// 付款时间
	private String userId;		// 用户id
	private Long addressId;		// 与用户地址表主键对应
	private Long logisticsId;		// 与物流表主键对应
	private Date deliveryDate;		// 发货时间
	private String delFlag;		// 是否删除
	private String deliveryName;		// 收货人姓名
	private String deliveryAddress;		// 到达地址
	private String deliveryPhone;		// 到达电话
	private String totalCount;
	private String tradeNo;//支付宝交易号支付宝专用
	public Order() {
		this(null);
	}

	public Order(String id){
		super(id);
	}
	
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	@Length(min=0, max=255, message="订单编号长度不能超过 255 个字符")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=2, message="订单状态 0未付款长度不能超过 2 个字符")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Length(min=0, max=2, message="商品类别数量长度不能超过 2 个字符")
	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}
	
	@Length(min=0, max=64, message="商品总价长度不能超过 64 个字符")
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Length(min=0, max=64, message="运费金额长度不能超过 64 个字符")
	public String getLogisticsPrice() {
		return logisticsPrice;
	}

	public void setLogisticsPrice(String logisticsPrice) {
		this.logisticsPrice = logisticsPrice;
	}
	
	@Length(min=0, max=64, message="折扣价格长度不能超过 64 个字符")
	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	@Length(min=0, max=64, message="真实支付价格长度不能超过 64 个字符")
	public String getReallyPrice() {
		return reallyPrice;
	}

	public void setReallyPrice(String reallyPrice) {
		this.reallyPrice = reallyPrice;
	}
	
	@Length(min=0, max=64, message="支付类型长度不能超过 64 个字符")
	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	@Length(min=0, max=64, message="第三方支付编号长度不能超过 64 个字符")
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	public Long getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Long logisticsId) {
		this.logisticsId = logisticsId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@Length(min=0, max=1, message="是否删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Length(min=0, max=64, message="收货人姓名长度不能超过 64 个字符")
	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	@Length(min=0, max=64, message="到达电话长度不能超过 64 个字符")
	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}
	@Length(min=0, max=64, message="tradeNo")
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	
}