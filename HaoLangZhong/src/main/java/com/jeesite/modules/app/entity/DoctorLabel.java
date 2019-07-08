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
 * sys_doctor_labelEntity
 * @author 范耘诚
 * @version 2019-07-08
 */
@Table(name="sys_doctor_label", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="type", attrName="type", label="标签类型", comment="标签类型：1.身体部位，2.接受疗法，3.技能分类"),
		@Column(name="content", attrName="content", label="标签内容"),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="del_flag"),
	}, orderBy="a.update_date DESC"
)
public class DoctorLabel extends DataEntity<DoctorLabel> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 标签类型：1.身体部位，2.接受疗法，3.技能分类
	private String content;		// 标签内容
	private String delFlag;		// del_flag
	
	public DoctorLabel() {
		this(null);
	}

	public DoctorLabel(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="标签类型长度不能超过 64 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="标签内容长度不能超过 64 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="del_flag长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}