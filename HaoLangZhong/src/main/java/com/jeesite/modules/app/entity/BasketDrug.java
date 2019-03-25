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
 * sys_basket_drugEntity
 * @author 范耘诚
 * @version 2019-03-11
 */
@Table(name="sys_basket_drug", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="create_date", attrName="createDate", label="创建时间"),
		@Column(name="update_date", attrName="updateDate", label="更新时间"),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="update_by", attrName="updateBy", label="更新人"),
		@Column(name="del_flag", attrName="delFlag", label="0未删除,1删除"),
		@Column(name="basket_id", attrName="basketId", label="与sys_basket 购物车表主键对应"),
		@Column(name="drug_id", attrName="drugId", label="与sys_air_drug药品表主键对应"),
		@Column(name="lose_flag", attrName="loseFlag", label="是否失效0", comment="是否失效0:未失效,1失效"),
		@Column(name="lose_remarks", attrName="loseRemarks", label="失效原因", queryType=QueryType.LIKE),
		@Column(name="drug_count", attrName="drugCount", label="药品数量"),
	}, orderBy="a.update_date DESC"
)
public class BasketDrug extends DataEntity<BasketDrug> {
	
	private static final long serialVersionUID = 1L;
	private String delFlag;		// 0未删除,1删除
	private Long basketId;		// 与sys_basket 购物车表主键对应
	private Long drugId;		// 与sys_air_drug药品表主键对应
	private String loseFlag;		// 是否失效0:未失效,1失效
	private String loseRemarks;		// 失效原因
	private Long drugCount;		// 药品数量
	
	public BasketDrug() {
		this(null);
	}

	public BasketDrug(String id){
		super(id);
	}
	
	@Length(min=0, max=1, message="0未删除,1删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public Long getBasketId() {
		return basketId;
	}

	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}
	
	public Long getDrugId() {
		return drugId;
	}

	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	
	@Length(min=0, max=1, message="是否失效0长度不能超过 1 个字符")
	public String getLoseFlag() {
		return loseFlag;
	}

	public void setLoseFlag(String loseFlag) {
		this.loseFlag = loseFlag;
	}
	
	public String getLoseRemarks() {
		return loseRemarks;
	}

	public void setLoseRemarks(String loseRemarks) {
		this.loseRemarks = loseRemarks;
	}
	
	public Long getDrugCount() {
		return drugCount;
	}

	public void setDrugCount(Long drugCount) {
		this.drugCount = drugCount;
	}
	
}