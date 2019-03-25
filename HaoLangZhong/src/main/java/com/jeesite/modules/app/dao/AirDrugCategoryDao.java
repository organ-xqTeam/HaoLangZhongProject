/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.AirDrugCategory;

/**
 * sys_air_drug_categoryDAO接口
 * @author 范耘诚
 * @version 2019-03-05
 */
@MyBatisDao
public interface AirDrugCategoryDao extends CrudDao<AirDrugCategory> {
	/**搜索中成药(药品类别)分类集合*/
	List<Map> queryList(AirDrugCategory airDrugCategory);
	
}