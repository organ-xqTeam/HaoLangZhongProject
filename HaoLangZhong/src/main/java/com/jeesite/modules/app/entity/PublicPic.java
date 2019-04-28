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
 * sys_public_picEntity
 * @author 范耘诚
 * @version 2019-04-24
 */
@Table(name="sys_public_pic", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="publicid", attrName="publicid", label="免费咨询id"),
		@Column(name="pic", attrName="pic", label="咨询图片"),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="del_flag"),
	}, orderBy="a.update_date DESC"
)
public class PublicPic extends DataEntity<PublicPic> {
	
	private static final long serialVersionUID = 1L;
	private String publicid;		// 咨询id
	private String pic;		// 咨询图片
	private String delFlag;		// del_flag
	
	public PublicPic() {
		this(null);
	}

	public PublicPic(String id){
		super(id);
	}
	

	
	public String getPublicid() {
		return publicid;
	}

	public void setPublicid(String publicid) {
		this.publicid = publicid;
	}

	@Length(min=0, max=64, message="咨询图片长度不能超过 64 个字符")
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	@Length(min=0, max=1, message="del_flag长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}