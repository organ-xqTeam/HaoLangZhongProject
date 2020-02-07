package com.jeesite.modules.app.utils;

/**
 * 分页实体
 */
public class PageModel {
	
	// 页码
	private int start;
	// 页大小
	private int end;
	
	public PageModel(int pageNum, int pageSize) throws Exception{
		this.setStart((pageNum-1)*pageSize);
		this.setEnd(pageSize);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
}