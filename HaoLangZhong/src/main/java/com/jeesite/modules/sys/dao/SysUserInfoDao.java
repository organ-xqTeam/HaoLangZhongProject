/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.SysAirDrug;
import com.jeesite.modules.sys.entity.SysUserInfo;

/**
 * sys_user_infoDAO接口
 * @author a
 * @version 2019-03-12
 */
@MyBatisDao
public interface SysUserInfoDao extends CrudDao<SysUserInfo> {
	
	List<SysUserInfo> queryList(SysUserInfo u);
	
	int queryCount(SysUserInfo u);
	
	int deleteDoctor(@Param("id") String id);
	
}