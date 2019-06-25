package com.jeesite.modules.app.entity;

import java.util.List;

public class DoctorInfo extends BaseEntity {

	private List<String> labels;
	
	private int count;
	
	private String id;
	
	private String hot;
	
	private String recommend;
	
	private String cityid;
	
	private String ownFlag;
	
	private String starLv;
	public List<String> getLabels() {
		return labels;
	}
	
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	public int getCount() {
		return count;
	}
	

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
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

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getOwnFlag() {
		return ownFlag;
	}

	public void setOwnFlag(String ownFlag) {
		this.ownFlag = ownFlag;
	}

	public String getStarLv() {
		return starLv;
	}

	public void setStarLv(String starLv) {
		this.starLv = starLv;
	}
	
	
}
