/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.entity;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * sys_area_infoEntity
 * @author 范耘诚
 * @version 2019-04-11
 */
@Table(name="sys_area_info", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="名称", queryType=QueryType.LIKE),
		@Column(name="arealevel", attrName="arealevel", label="层级标识", comment="层级标识： 1  省份， 2  市， 3  区县"),
		@Column(name="parent_id", attrName="parentId", label="父节点"),
	}, orderBy="  a.name LIKE '%北京%' DESC,a.name LIKE '%上海%' DESC,a.name LIKE '%广东省%' DESC,a.name LIKE '%浙江%' DESC,a.name LIKE '%辽宁%' DESC"
)
public class AreaInfo extends DataEntity<AreaInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Integer arealevel;		// 层级标识： 1  省份， 2  市， 3  区县
	private Long parentId;		// 父节点
	
	private List child;
	
	public AreaInfo() {
		this(null);
	}

	public AreaInfo(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getArealevel() {
		return arealevel;
	}

	public void setArealevel(Integer arealevel) {
		this.arealevel = arealevel;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List getChild() {
		return child;
	}

	public void setChild(List child) {
		this.child = child;
	}

	

	
	
}