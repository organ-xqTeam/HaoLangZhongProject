/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.DiscountDetail;

/**
 * sys_discount_detailDAO接口
 * @author 范耘诚
 * @version 2019-03-12
 */
@MyBatisDao
public interface DiscountDetailDao extends CrudDao<DiscountDetail> {
	/**增加折扣从详情表的操作*/
	void insertDiscountDetailMap(Map discountDetailMap);
	
}