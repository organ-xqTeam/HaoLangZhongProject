/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.Order;

/**
 * sys_orderDAO接口
 * @author 范耘诚
 * @version 2019-03-13
 */
@MyBatisDao
public interface OrderDao extends CrudDao<Order> {
	/**插入订单*/
	void insertOrder(Map<String, Object> orderParmMap);
	/**通过OrderNo 查找到唯一的订单id*/
	Integer selectIdByOrderNo(Map<String, Object> orderParmMap);
	/**通过userId和订单状态查找集合*/
	List<Map> findOrderInfoByOrderStatusAndUserId(Map parmMap);
	/**软删除订单*/
	void updateDelFlagOrderByOrderId(Map parmMap);
	/**订单支付页成功假*/
	void buySuccessOrder(Map parmMap);
	/**通过id修改订单状态*/
	void changeOrderStatusByOrderId(Map parmMap);
	/**通过订单id获取此订单下的所有药品id**/
	List getDrugIdByOrderId(Map parmMap);
	
}