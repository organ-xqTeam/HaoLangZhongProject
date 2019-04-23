package com.jeesite.modules.sys.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.utils.PageModel;
@Table(name="sys_user_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="mobile", attrName="mobile", label="手机号"),
		@Column(name="password", attrName="password", label="密码"),
		@Column(name="icon", attrName="icon", label="头像"),
		@Column(name="regtime", attrName="regtime", label="注册时间"),
		@Column(name="lastlogin", attrName="lastlogin", label="最后登录时间"),
		@Column(name="islock", attrName="islock", label="是否被锁定", comment="是否被锁定：1.未锁定，0.已锁定"),
		@Column(name="isauthentication", attrName="isauthentication", label="是否完成认证", comment="是否完成认证：1.未认证，0.已认证，2.未通过"),
		@Column(name="type", attrName="type", label="类型", comment="类型：1.普通用户，2.医生"),
		@Column(name="create_by", attrName="createBy", label="create_by", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="create_date", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="update_by", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="update_date", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
		@Column(name="del_flag", attrName="delFlag", label="del_flag"),
		@Column(name="nike_name", attrName="nikeName", label="昵称", queryType=QueryType.LIKE),
		@Column(name="is_invite", attrName="isInvite", label="是否是被邀请的用户 0不是被邀请用户 ,1是被邀请的用户"),
	}, orderBy="a.update_date DESC"
)
public class SysUserInfo extends DataEntity<SysUserInfo> {
	
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
	private String    orderBy;
	private PageModel pageModel;
	private boolean   isNewRecord;
	private static final long serialVersionUID = 1L;
	private String mobile;		// 手机号
	private String password;		// 密码
	private String icon;		// 头像
	private String regtime;		// 注册时间
	private String lastlogin;		// 最后登录时间
	private String islock;		// 是否被锁定：1.未锁定，0.已锁定
	private String type;		// 类型：1.普通用户，2.医生
	private String delFlag;		// del_flag
	private String isInvite;		// 是否是被邀请的用户 0不是被邀请用户 ,1是被邀请的用户
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getIslock() {
		return islock;
	}
	public void setIslock(String islock) {
		this.islock = islock;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getIsInvite() {
		return isInvite;
	}
	public void setIsInvite(String isInvite) {
		this.isInvite = isInvite;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}
	
	

}