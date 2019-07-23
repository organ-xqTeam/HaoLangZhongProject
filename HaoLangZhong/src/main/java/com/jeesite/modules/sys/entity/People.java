/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * peopleEntity
 * @author 范耘诚
 * @version 2019-07-19
 */
@Table(name="people", alias="a", columns={
		@Column(name="id", attrName="id", label="zhujian", isPK=true),
		@Column(name="name", attrName="name", label="name", queryType=QueryType.LIKE),
		@Column(name="age", attrName="age", label="age"),
	}, orderBy="a.id DESC"
)
public class People extends DataEntity<People> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String age;		// age
	private List<PeopleDet> peopleDetList = ListUtils.newArrayList();		// 子表列表
	
	public People() {
		this(null);
	}

	public People(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="name长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="age长度不能超过 64 个字符")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public List<PeopleDet> getPeopleDetList() {
		return peopleDetList;
	}

	public void setPeopleDetList(List<PeopleDet> peopleDetList) {
		this.peopleDetList = peopleDetList;
	}
	
}