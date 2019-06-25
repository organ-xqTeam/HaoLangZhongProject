/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.SysDoctorInfo;

/**
 * sys_doctor_infoDAO接口
 * @author lll
 * @version 2019-05-29
 */
@MyBatisDao
public interface SysDoctorInfoDao extends CrudDao<SysDoctorInfo> {
	
}