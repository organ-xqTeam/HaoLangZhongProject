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
 * sys_doctor_picEntity
 * @author 范耘诚
 * @version 2019-04-04
 */
@Table(name="sys_doctor_pic", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="doctorid", attrName="doctorid", label="医生id"),
		@Column(name="certificate1", attrName="certificate1", label="证书，图片1，id"),
		@Column(name="certificate2", attrName="certificate2", label="证书，图片2，id"),
		@Column(name="certificate3", attrName="certificate3", label="证书，图片3，id"),
		@Column(name="introducepic1", attrName="introducepic1", label="服务介绍图片1"),
		@Column(name="introducepic2", attrName="introducepic2", label="服务介绍图片2"),
		@Column(name="introducepic3", attrName="introducepic3", label="服务介绍图片3"),
		@Column(name="introducevideo", attrName="introducevideo", label="服务介绍视频1"),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="del_flag"),
	}, orderBy="a.update_date DESC"
)
public class DoctorPic extends DataEntity<DoctorPic> {
	
	private static final long serialVersionUID = 1L;
	private String doctorid;		// 医生id
	private String certificate1;		// 证书，图片1，id
	private String certificate2;		// 证书，图片2，id
	private String certificate3;		// 证书，图片3，id
	private String introducepic1;		// 服务介绍图片1
	private String introducepic2;		// 服务介绍图片2
	private String introducepic3;		// 服务介绍图片3
	private String introducevideo;		// 服务介绍视频1
	private String delFlag;		// del_flag
	
	public DoctorPic() {
		this(null);
	}

	public DoctorPic(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="医生id长度不能超过 64 个字符")
	public String getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}
	
	@Length(min=0, max=64, message="证书，图片1，id长度不能超过 64 个字符")
	public String getCertificate1() {
		return certificate1;
	}

	public void setCertificate1(String certificate1) {
		this.certificate1 = certificate1;
	}
	
	@Length(min=0, max=64, message="证书，图片2，id长度不能超过 64 个字符")
	public String getCertificate2() {
		return certificate2;
	}

	public void setCertificate2(String certificate2) {
		this.certificate2 = certificate2;
	}
	
	@Length(min=0, max=64, message="证书，图片3，id长度不能超过 64 个字符")
	public String getCertificate3() {
		return certificate3;
	}

	public void setCertificate3(String certificate3) {
		this.certificate3 = certificate3;
	}
	
	@Length(min=0, max=64, message="服务介绍图片1长度不能超过 64 个字符")
	public String getIntroducepic1() {
		return introducepic1;
	}

	public void setIntroducepic1(String introducepic1) {
		this.introducepic1 = introducepic1;
	}
	
	@Length(min=0, max=64, message="服务介绍图片2长度不能超过 64 个字符")
	public String getIntroducepic2() {
		return introducepic2;
	}

	public void setIntroducepic2(String introducepic2) {
		this.introducepic2 = introducepic2;
	}
	
	@Length(min=0, max=64, message="服务介绍图片3长度不能超过 64 个字符")
	public String getIntroducepic3() {
		return introducepic3;
	}

	public void setIntroducepic3(String introducepic3) {
		this.introducepic3 = introducepic3;
	}
	
	@Length(min=0, max=64, message="服务介绍视频1长度不能超过 64 个字符")
	public String getIntroducevideo() {
		return introducevideo;
	}

	public void setIntroducevideo(String introducevideo) {
		this.introducevideo = introducevideo;
	}
	
	@Length(min=0, max=1, message="del_flag长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}