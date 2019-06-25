/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.HospitalInfo;

/**
 * sys_hospital_infoDAO接口
 * @author 范耘诚
 * @version 2019-05-29
 */
@MyBatisDao
public interface HospitalInfoDao extends CrudDao<HospitalInfo> {
	
}