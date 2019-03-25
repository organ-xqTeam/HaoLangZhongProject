/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.OrderDetail;

/**
 * 订单向平详情表DAO接口
 * @author 范耘诚
 * @version 2019-03-13
 */
@MyBatisDao
public interface OrderDetailDao extends CrudDao<OrderDetail> {
	/**批量增加订单吗详情表*/
	void insertOrderDetail(Map orderDetailMap);
	
}