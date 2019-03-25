package com.jeesite.modules.sys.entity;

public class SysUserInfo extends BaseEntity {
	
	private String id;
	private String name;				// 姓名
	private String sex;					// 性别
	private String age;					// 年龄 
	private String idcard;				// 身份证
	private String classify;			// 分类
	private String nikeName;			// 昵称
	private String telephone;			// 电话	
	private String technical;			// 职称
	private String isauthentication;	// 是否完成认证
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getNikeName() {
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTechnical() {
		return technical;
	}
	public void setTechnical(String technical) {
		this.technical = technical;
	}
	public String getIsauthentication() {
		return isauthentication;
	}
	public void setIsauthentication(String isauthentication) {
		this.isauthentication = isauthentication;
	}

}