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
 * sys_air_ad_picEntity
 * @author 范耘诚
 * @version 2019-03-06
 */
@Table(name="sys_air_ad_pic", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="图片名称", queryType=QueryType.LIKE),
		@Column(name="memo", attrName="memo", label="图片备注"),
		@Column(name="pic_url", attrName="picUrl", label="图片路径"),
		@Column(name="activity_url", attrName="activityUrl", label="活动页地址"),
		@Column(name="create_date", attrName="createDate", label="创建时间"),
		@Column(name="update_date", attrName="updateDate", label="更新时间"),
		@Column(name="is_use", attrName="isUse", label="是否使用 0不启用 1启用"),
		@Column(name="is_flag", attrName="isFlag", label="是否删除 0不删除 1删除"),
		@Column(name="del_flag", attrName="delFlag", label="是否删除 0不删除 1删除"),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="update_by", attrName="updateBy", label="更新人"),
		@Column(name="remarks", attrName="remarks", label="作者", queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class AirAdPic extends DataEntity<AirAdPic> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 图片名称
	private String memo;		// 图片备注
	private String picUrl;		// 图片路径
	private String activityUrl;		// 活动页地址
	private String isUse;		// 是否使用 0不启用 1启用		
	private String delFlag;		// 是否删除 0不删除 1删除
	private Date createDate;  //创建日期
	private Date updateDate; //更新日期
	private String updateBy; //更新人
	private String createBy; //创建人
	private String  remarks;
	public AirAdPic() {
		this(null);
	}

	public AirAdPic(String id){
		super(id);
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

	@Length(min=0, max=255, message="图片名称长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="图片备注长度不能超过 255 个字符")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=255, message="图片路径长度不能超过 255 个字符")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=255, message="活动页地址长度不能超过 255 个字符")
	public String getActivityUrl() {
		return activityUrl;
	}

	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}
	
	@Length(min=0, max=255, message="是否使用 0不启用 1启用长度不能超过 255 个字符")
	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	

	
	@Length(min=0, max=1, message="是否删除 0不删除 1删除长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}