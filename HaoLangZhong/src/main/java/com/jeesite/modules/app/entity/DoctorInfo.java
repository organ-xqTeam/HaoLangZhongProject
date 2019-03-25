package com.jeesite.modules.app.entity;

import java.util.List;

public class DoctorInfo extends BaseEntity {

	private List<String> labels;
	
	private int count;
	
	private String id;
	
	public List<String> getLabels() {
		return labels;
	}
	
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
