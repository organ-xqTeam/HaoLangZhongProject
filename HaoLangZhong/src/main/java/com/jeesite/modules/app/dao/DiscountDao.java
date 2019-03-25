/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.Discount;

/**
 * sys_discountDAO接口
 * @author 范耘诚
 * @version 2019-03-12
 */
@MyBatisDao
public interface DiscountDao extends CrudDao<Discount> {
	/**通过userId查找唯一*/
	Map selectByUserId(Map parmMap);
	/**插入数据*/
	void insertDiscount(Map parmMap);
	
	
}