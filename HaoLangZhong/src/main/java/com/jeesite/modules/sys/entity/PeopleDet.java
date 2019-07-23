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
 * peopleEntity
 * @author 范耘诚
 * @version 2019-07-19
 */
@Table(name="people_det", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="num", attrName="num", label="num"),
		@Column(name="people_id", attrName="peopleId.id", label="people_id"),
	}, orderBy="a.id ASC"
)
public class PeopleDet extends DataEntity<PeopleDet> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// num
	private People peopleId;		// people_id 父类
	
	public PeopleDet() {
		this(null);
	}


	public PeopleDet(People peopleId){
		this.peopleId = peopleId;
	}
	
	@Length(min=0, max=64, message="num长度不能超过 64 个字符")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public People getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(People peopleId) {
		this.peopleId = peopleId;
	}
	
}