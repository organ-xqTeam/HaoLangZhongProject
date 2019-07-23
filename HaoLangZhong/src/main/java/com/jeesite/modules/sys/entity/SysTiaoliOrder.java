/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.entity;

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
 * sys_tiaoli_orderEntity
 * @author 范耘诚
 * @version 2019-07-22
 */
@Table(name="sys_tiaoli_order", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="come_cost", attrName="comeCost", label="come_cost"),
		@Column(name="count", attrName="count", label="count"),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="del_flag", attrName="delFlag", label="del_flag"),

		@Column(name="order_no", attrName="orderNo", label="order_no"),
		@Column(name="order_status", attrName="orderStatus", label="order_status"),
		@Column(name="out_trade_no", attrName="outTradeNo", label="out_trade_no"),
		@Column(name="pay_channel", attrName="payChannel", label="pay_channel"),
		@Column(name="pay_date", attrName="payDate", label="pay_date"),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="total_price", attrName="totalPrice", label="total_price"),
		@Column(name="trade_no", attrName="tradeNo", label="trade_no"),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		/*@Column(name="userid", attrName="userid", label="userid"),*/
		@Column(name="userid", attrName="sysUserInfo.id", label="userid"),
		@Column(name="docid", attrName="sysUserInfoDoc.id", label="docid"),
	}, // 支持联合查询，如左右连接查询，支持设置查询自定义关联表的返回字段列
	joinTable={
		@JoinTable(type=Type.JOIN, entity=SysUserInfo.class, alias="sad", 
			on="a.userid = sad.id",
			columns={@Column(includeEntity=SysUserInfo.class)}),
		
		@JoinTable(type=Type.JOIN, entity=SysUserInfo.class,attrName="sysUserInfoDoc", alias="dd", 
		on="a.docid = dd.id",
		columns={@Column(includeEntity=SysUserInfo.class)}),
	},orderBy="a.update_date DESC"
)
public class SysTiaoliOrder extends DataEntity<SysTiaoliOrder> {
	
	private static final long serialVersionUID = 1L;
	private String comeCost;		// come_cost
	private String count;		// count
	private String delFlag;		// del_flag
	private String docid;		// docid
	private String orderNo;		// order_no
	private String orderStatus;		// order_status
	private String outTradeNo;		// out_trade_no
	private String payChannel;		// pay_channel
	private Date payDate;		// pay_date
	private String totalPrice;		// total_price
	private String tradeNo;		// trade_no
	private String userid;		// userid
	private SysUserInfo sysUserInfo;
	private SysUserInfo sysUserInfoDoc;
	private SysDoctorInfo sysDoctorInfo;
	
	public SysTiaoliOrder() {
		this(null);
	}

	public SysTiaoliOrder(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="come_cost长度不能超过 64 个字符")
	public String getComeCost() {
		return comeCost;
	}

	public void setComeCost(String comeCost) {
		this.comeCost = comeCost;
	}
	
	@Length(min=0, max=64, message="count长度不能超过 64 个字符")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@Length(min=0, max=64, message="del_flag长度不能超过 64 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Length(min=0, max=64, message="docid长度不能超过 64 个字符")
	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}
	
	@Length(min=0, max=64, message="order_no长度不能超过 64 个字符")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="order_status长度不能超过 64 个字符")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Length(min=0, max=64, message="out_trade_no长度不能超过 64 个字符")
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	@Length(min=0, max=64, message="pay_channel长度不能超过 64 个字符")
	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	@Length(min=0, max=64, message="total_price长度不能超过 64 个字符")
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Length(min=0, max=64, message="trade_no长度不能超过 64 个字符")
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	@Length(min=0, max=64, message="userid长度不能超过 64 个字符")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public SysUserInfo getSysUserInfo() {
		return sysUserInfo;
	}
	@Length(min=0, max=64, message="userid长度不能超过 64 个字符")
	public void setSysUserInfo(SysUserInfo sysUserInfo) {
		this.sysUserInfo = sysUserInfo;
	}

	public SysDoctorInfo getSysDoctorInfo() {
		return sysDoctorInfo;
	}

	public void setSysDoctorInfo(SysDoctorInfo sysDoctorInfo) {
		this.sysDoctorInfo = sysDoctorInfo;
	}

	public SysUserInfo getSysUserInfoDoc() {
		return sysUserInfoDoc;
	}

	public void setSysUserInfoDoc(SysUserInfo sysUserInfoDoc) {
		this.sysUserInfoDoc = sysUserInfoDoc;
	}

	
	

	
	
	
	
}