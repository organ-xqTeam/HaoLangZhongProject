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
 * sys_discount_detailEntity
 * @author 范耘诚
 * @version 2019-03-15
 */
@Table(name="sys_discount_detail", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="discount_id", attrName="discountId", label="sys_discount 折扣表相关联"),
		@Column(name="remarks", attrName="remarks", label="明细", queryType=QueryType.LIKE),
		@Column(name="detail_price", attrName="detailPrice", label="明细价格﹢收入,-支出", comment="明细价格﹢收入,-支出(单位:分)"),
		@Column(name="detail_state", attrName="detailState", label="0收入,1支出"),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
		@Column(name="del_flag", attrName="delFlag", label="更新人", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class DiscountDetail extends DataEntity<DiscountDetail> {
	
	private static final long serialVersionUID = 1L;
	private Long discountId;		// sys_discount 折扣表相关联
	private Integer detailPrice;		// 明细价格﹢收入,-支出(单位:分)
	private String detailState;		// 0收入,1支出
	
	private String delFlag;
	
	public DiscountDetail() {
		this(null);
	}

	public DiscountDetail(String id){
		super(id);
	}
	
	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}
	
	public Integer getDetailPrice() {
		return detailPrice;
	}

	public void setDetailPrice(Integer detailPrice) {
		this.detailPrice = detailPrice;
	}
	
	@Length(min=0, max=10, message="0收入,1支出长度不能超过 10 个字符")
	public String getDetailState() {
		return detailState;
	}

	public void setDetailState(String detailState) {
		this.detailState = detailState;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	
}