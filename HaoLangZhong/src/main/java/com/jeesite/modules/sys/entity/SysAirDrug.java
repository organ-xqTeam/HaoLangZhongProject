/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * sys_air_drugEntity
 * @author 范耘诚
 * @version 2019-04-18
 */
@Table(name="sys_air_drug", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="药品名称", queryType=QueryType.LIKE),
		@Column(name="create_date", attrName="createDate", label="创建日期"),
		@Column(name="memo", attrName="memo", label="备注详情"),
		@Column(name="pay_price", attrName="payPrice", label="购买价格", comment="购买价格(字符串)单位:分"),
		@Column(name="pic1", attrName="pic1", label="图片1"),
		@Column(name="pic2", attrName="pic2", label="图片2"),
		@Column(name="pic3", attrName="pic3", label="图片3"),
		@Column(name="del_flag", attrName="delFlag", label="是否删除 0", comment="是否删除 0:不删除,1:删除"),
		@Column(name="drug_inventory_id", attrName="drugInventoryId", label="与sys_air_drug_comment关联的id", comment="与sys_air_drug_comment关联的id(库存id)"),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="first_flag", attrName="firstFlag", label="是否优先显示在推荐中 0", comment="是否优先显示在推荐中 0:不优先显示,1优先显示"),
		@Column(name="update_date", attrName="updateDate", label="更新时间"),
		@Column(name="update_by", attrName="updateBy", label="更新人"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
		@Column(name="drug_code", attrName="drugCode", label="审批编号"),
		@Column(name="drug_ln", attrName="drugLn", label="适应症"),
		@Column(name="component", attrName="component", label="成分"),
		@Column(name="character", attrName="character", label="性状"),
		@Column(name="indications", attrName="indications", label="主治"),
		@Column(name="spec", attrName="spec", label="规格"),
		@Column(name="consumption", attrName="consumption", label="用量"),
		@Column(name="effect", attrName="effect", label="不良反应"),
		@Column(name="taboo", attrName="taboo", label="禁忌"),
		@Column(name="careful", attrName="careful", label="注意事项"),
		@Column(name="toxicology", attrName="toxicology", label="药理毒理"),
		@Column(name="store", attrName="store", label="储藏"),
		@Column(name="packaging", attrName="packaging", label="包装"),
		@Column(name="validtime", attrName="validtime", label="有效时间"),
		@Column(name="standard", attrName="standard", label="执行标准"),
		@Column(name="approval", attrName="approval", label="审批文号"),
	}, orderBy="a.update_date DESC"
)
public class SysAirDrug extends DataEntity<SysAirDrug> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 药品名称
	private String memo;		// 备注详情
	private String payPrice;		// 购买价格(字符串)单位:分
	private String pic1;		// 图片1
	private String pic2;		// 图片2
	private String pic3;		// 图片3
	private String delFlag;		// 是否删除 0:不删除,1:删除
	private Long drugInventoryId;		// 与sys_air_drug_comment关联的id(库存id)
	private String firstFlag;		// 是否优先显示在推荐中 0:不优先显示,1优先显示
	private String drugCode;		// 审批编号
	private String drugLn;		// 适应症
	private String component;		// 成分
	private String character;		// 性状
	private String indications;		// 主治
	private String spec;		// 规格
	private String consumption;		// 用量
	private String effect;		// 不良反应
	private String taboo;		// 禁忌
	private String careful;		// 注意事项
	private String toxicology;		// 药理毒理
	private String store;		// 储藏
	private String packaging;		// 包装
	private String validtime;		// 有效时间
	private String standard;		// 执行标准
	private String approval;		// 审批文号
	
	public SysAirDrug() {
		this(null);
	}

	public SysAirDrug(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="药品名称长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="备注详情长度不能超过 255 个字符")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=10, message="购买价格长度不能超过 10 个字符")
	public String getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
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
	
	@Length(min=0, max=1, message="是否删除 0长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public Long getDrugInventoryId() {
		return drugInventoryId;
	}

	public void setDrugInventoryId(Long drugInventoryId) {
		this.drugInventoryId = drugInventoryId;
	}
	
	@Length(min=0, max=255, message="是否优先显示在推荐中 0长度不能超过 255 个字符")
	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}
	
	@Length(min=0, max=64, message="审批编号长度不能超过 64 个字符")
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	@Length(min=0, max=64, message="适应症长度不能超过 64 个字符")
	public String getDrugLn() {
		return drugLn;
	}

	public void setDrugLn(String drugLn) {
		this.drugLn = drugLn;
	}
	
	@Length(min=0, max=64, message="成分长度不能超过 64 个字符")
	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}
	
	@Length(min=0, max=64, message="性状长度不能超过 64 个字符")
	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}
	
	@Length(min=0, max=64, message="主治长度不能超过 64 个字符")
	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
	}
	
	@Length(min=0, max=64, message="规格长度不能超过 64 个字符")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@Length(min=0, max=64, message="用量长度不能超过 64 个字符")
	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}
	
	@Length(min=0, max=64, message="不良反应长度不能超过 64 个字符")
	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	@Length(min=0, max=64, message="禁忌长度不能超过 64 个字符")
	public String getTaboo() {
		return taboo;
	}

	public void setTaboo(String taboo) {
		this.taboo = taboo;
	}
	
	@Length(min=0, max=64, message="注意事项长度不能超过 64 个字符")
	public String getCareful() {
		return careful;
	}

	public void setCareful(String careful) {
		this.careful = careful;
	}
	
	@Length(min=0, max=64, message="药理毒理长度不能超过 64 个字符")
	public String getToxicology() {
		return toxicology;
	}

	public void setToxicology(String toxicology) {
		this.toxicology = toxicology;
	}
	
	@Length(min=0, max=64, message="储藏长度不能超过 64 个字符")
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}
	
	@Length(min=0, max=64, message="包装长度不能超过 64 个字符")
	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	
	@Length(min=0, max=64, message="有效时间长度不能超过 64 个字符")
	public String getValidtime() {
		return validtime;
	}

	public void setValidtime(String validtime) {
		this.validtime = validtime;
	}
	
	@Length(min=0, max=64, message="执行标准长度不能超过 64 个字符")
	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	@Length(min=0, max=255, message="审批文号长度不能超过 255 个字符")
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}
	
}