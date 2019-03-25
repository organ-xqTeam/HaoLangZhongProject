package com.jeesite.modules.sys.entity;

import com.jeesite.modules.app.utils.PageModel;

public class BaseEntity {

	private String    orderBy;
	private PageModel pageModel;
	private boolean   isNewRecord;

	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public boolean getIsNewRecord() {
		return isNewRecord;
	}
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}
	
}
