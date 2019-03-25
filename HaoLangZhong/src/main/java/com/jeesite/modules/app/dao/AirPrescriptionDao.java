/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.AirPrescription;

/**
 * 药方表DAO接口
 * @author 范耘诚
 * @version 2019-03-05
 */
@MyBatisDao
public interface AirPrescriptionDao extends CrudDao<AirPrescription> {
	/**插入数据*/
	void insertAirPrescription(AirPrescription insertAirPrescription);
	/**通过prescriptionIds们找到药方列表*/
	List<Map> getlistByPrescriptionIds(List prescriptionIdsList);
	
}