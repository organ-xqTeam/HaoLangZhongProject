/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.AirDrugLabel;

/**
 * 药品标签表DAO接口
 * @author 范耘诚
 * @version 2019-07-08
 */
@MyBatisDao
public interface AirDrugLabelDao extends CrudDao<AirDrugLabel> {
	
}