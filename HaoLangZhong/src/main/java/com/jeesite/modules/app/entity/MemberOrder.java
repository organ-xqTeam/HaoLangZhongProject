/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.entity;

import javax.validation.constraints.NotBlank;
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
 * sys_member_orderEntity
 * @author 范耘诚
 * @version 2019-06-28
 */
@Table(name="sys_member_order", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="order_no", attrName="orderNo", label="订单编号"),
		@Column(name="order_status", attrName="orderStatus", label="订单状态订单状态 0未付款 1已付款"),
		@Column(name="total_price", attrName="totalPrice", label="订单总价"),
		@Column(name="userid", attrName="userid", label="用户id"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="out_trade_no", attrName="outTradeNo", label="第三方编号"),
		@Column(name="pay_date", attrName="payDate", label="支付时间"),
		@Column(name="trade_no", attrName="tradeNo", label="支付宝专用的号"),
		@Column(name="pay_channel", attrName="payChannel", label="支付类型"),
		@Column(name="del_flag", attrName="delFlag", label="是否删除"),
	}, orderBy="a.update_date DESC"
)
public class MemberOrder extends DataEntity<MemberOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单编号
	private String orderStatus;		// 订单状态订单状态 0未付款 1已付款
	private String totalPrice;		// 订单总价
	private String userid;		// 用户id
	private String outTradeNo;		// 第三方编号
	private Date payDate;		// 支付时间
	private String tradeNo;		// 支付宝专用的号
	private String payChannel;		// 支付类型
	private String delFlag;		// 是否删除
	
	public MemberOrder() {
		this(null);
	}

	public MemberOrder(String id){
		super(id);
	}
	
	@NotBlank(message="订单编号不能为空")
	@Length(min=0, max=64, message="订单编号长度不能超过 64 个字符")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="订单状态订单状态 0未付款 1已付款长度不能超过 64 个字符")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Length(min=0, max=64, message="订单总价长度不能超过 64 个字符")
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Length(min=0, max=64, message="用户id长度不能超过 64 个字符")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=64, message="第三方编号长度不能超过 64 个字符")
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
	
	@Length(min=0, max=64, message="支付宝专用的号长度不能超过 64 个字符")
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	@Length(min=0, max=64, message="支付类型长度不能超过 64 个字符")
	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	@Length(min=0, max=64, message="是否删除长度不能超过 64 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}