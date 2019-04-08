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
 * 系统消息表Entity
 * @author 范耘诚
 * @version 2019-04-02
 */
@Table(name="sys_message", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="title", attrName="title", label="标题", queryType=QueryType.LIKE),
		@Column(name="content", attrName="content", label="内容"),
		@Column(name="pic", attrName="pic", label="图片"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建人", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新人", isQuery=false),
		@Column(name="author", attrName="author", label="作者"),
	}, orderBy="a.update_date DESC"
)
public class Message extends DataEntity<Message> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String content;		// 内容
	private String pic;		// 图片
	private String author;		// 作者
	
	public Message() {
		this(null);
	}

	public Message(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="标题长度不能超过 64 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="图片长度不能超过 64 个字符")
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	@Length(min=0, max=64, message="作者长度不能超过 64 个字符")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}