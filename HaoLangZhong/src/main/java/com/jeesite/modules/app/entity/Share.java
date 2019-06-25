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
 * sys_shareEntity
 * @author 范耘诚
 * @version 2019-06-17
 */
@Table(name="sys_share", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="title", attrName="title", label="标题", queryType=QueryType.LIKE),
		@Column(name="picurl", attrName="picurl", label="图片地址"),
		@Column(name="shareurl", attrName="shareurl", label="分享的链接"),
		@Column(name="content", attrName="content", label="分享的内容"),
	}, orderBy="a.id DESC"
)
public class Share extends DataEntity<Share> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String picurl;		// 图片地址
	private String shareurl;		// 分享的链接
	private String content;		// 分享的内容
	
	private String shareCode;  //邀请码不走数据库
	
	public Share() {
		this(null);
	}

	public Share(String id){
		super(id);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	public String getShareurl() {
		return shareurl;
	}

	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}
	
	
	
	
}