/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * sys_doctor_infoEntity
 * @author lll
 * @version 2019-05-29
 */
@Table(name="sys_doctor_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="doctorid", attrName="doctorid", label="医生id"),
		@Column(name="doctornum", attrName="doctornum", label="医生编号"),
		@Column(name="name", attrName="name", label="姓名", queryType=QueryType.LIKE),
		@Column(name="sex", attrName="sex", label="性别", comment="性别：1.男；2.女"),
		@Column(name="age", attrName="age", label="年龄", comment="年龄（生日）：****年**月**日"),
		@Column(name="telephone", attrName="telephone", label="电话"),
		@Column(name="idcard", attrName="idcard", label="身份证"),
		@Column(name="adress", attrName="adress", label="地址"),
		@Column(name="technical", attrName="technical", label="职称", comment="职称：1.医师；2.主治医师；3.副主任医师；4.主任医师"),
		@Column(name="classify", attrName="classify", label="分类", comment="分类：1.师承；2.祖传；3.院校"),
		@Column(name="introduce", attrName="introduce", label="服务介绍文字"),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="cost", attrName="cost", label="咨询费用"),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="del_flag"),
		@Column(name="cityid", attrName="cityid", label="城市id"),
		@Column(name="come_flag", attrName="comeFlag", label="允许医生上门服务的flag"),
		@Column(name="come_cost", attrName="comeCost", label="上门服务金额"),
		@Column(name="own_flag", attrName="ownFlag", label="是否是本院医生的  0不属于  1属于"),
		@Column(name="first_count", attrName="firstCount", label="权重"),
		@Column(name="star_lv", attrName="starLv", label="医生星级  共5级", comment="医生星级  共5级（一星级，二星级，三星级，四星级，五星级，对应价格分别为：80,180,280,380,480元线下交易默认为1"),
	}, orderBy="a.update_date DESC"
)
public class SysDoctorInfo extends DataEntity<SysDoctorInfo> {
	
	private static final long serialVersionUID = 1L;
	private String doctorid;		// 医生id
	private String doctornum;		// 医生编号
	private String name;		// 姓名
	private String sex;		// 性别：1.男；2.女
	private Date age;		// 年龄（生日）：****年**月**日
	private String telephone;		// 电话
	private String idcard;		// 身份证
	private String adress;		// 地址
	private String technical;		// 职称：1.医师；2.主治医师；3.副主任医师；4.主任医师
	private String classify;		// 分类：1.师承；2.祖传；3.院校
	private String introduce;		// 服务介绍文字
	private String cost;		// 咨询费用
	private String delFlag;		// del_flag
	private String cityid;		// 城市id
	private String comeFlag;		// 允许医生上门服务的flag
	private String comeCost;		// 上门服务金额
	private String ownFlag;		// 是否是本院医生的  0不属于  1属于
	private Long firstCount;		// 权重
	private String starLv;		// 医生星级  共5级（一星级，二星级，三星级，四星级，五星级，对应价格分别为：80,180,280,380,480元线下交易默认为1
	
	public SysDoctorInfo() {
		this(null);
	}

	public SysDoctorInfo(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="医生id长度不能超过 64 个字符")
	public String getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}
	
	@Length(min=0, max=64, message="医生编号长度不能超过 64 个字符")
	public String getDoctornum() {
		return doctornum;
	}

	public void setDoctornum(String doctornum) {
		this.doctornum = doctornum;
	}
	
	@Length(min=0, max=64, message="姓名长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="性别长度不能超过 64 个字符")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAge() {
		return age;
	}

	public void setAge(Date age) {
		this.age = age;
	}
	
	@Length(min=0, max=64, message="电话长度不能超过 64 个字符")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=64, message="身份证长度不能超过 64 个字符")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@Length(min=0, max=64, message="地址长度不能超过 64 个字符")
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	@Length(min=0, max=64, message="职称长度不能超过 64 个字符")
	public String getTechnical() {
		return technical;
	}

	public void setTechnical(String technical) {
		this.technical = technical;
	}
	
	@Length(min=0, max=64, message="分类长度不能超过 64 个字符")
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Length(min=0, max=64, message="咨询费用长度不能超过 64 个字符")
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	@Length(min=0, max=1, message="del_flag长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Length(min=0, max=64, message="城市id长度不能超过 64 个字符")
	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	
	@Length(min=0, max=1, message="允许医生上门服务的flag长度不能超过 1 个字符")
	public String getComeFlag() {
		return comeFlag;
	}

	public void setComeFlag(String comeFlag) {
		this.comeFlag = comeFlag;
	}
	
	@Length(min=0, max=10, message="上门服务金额长度不能超过 10 个字符")
	public String getComeCost() {
		return comeCost;
	}

	public void setComeCost(String comeCost) {
		this.comeCost = comeCost;
	}
	
	@Length(min=0, max=10, message="是否是本院医生的  0不属于  1属于长度不能超过 10 个字符")
	public String getOwnFlag() {
		return ownFlag;
	}

	public void setOwnFlag(String ownFlag) {
		this.ownFlag = ownFlag;
	}
	
	public Long getFirstCount() {
		return firstCount;
	}

	public void setFirstCount(Long firstCount) {
		this.firstCount = firstCount;
	}
	
	@Length(min=0, max=64, message="医生星级  共5级长度不能超过 64 个字符")
	public String getStarLv() {
		return starLv;
	}

	public void setStarLv(String starLv) {
		this.starLv = starLv;
	}
	
}