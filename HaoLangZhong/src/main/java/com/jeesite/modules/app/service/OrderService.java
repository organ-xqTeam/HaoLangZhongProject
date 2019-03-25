package com.jeesite.modules.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.OrderDao;
import com.jeesite.modules.app.entity.Order;
/**
 * 
 * @author 范耘诚
 * 2019.3.13
 *
 */
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
@Service
public class OrderService  extends CrudService<OrderDao,Order> {
	@Autowired
	private OrderDao orderDao;
	/**插入订单并返回Id*/
	public Integer insertOrderReturnId(Map<String, Object> orderParmMap) {
		// TODO Auto-generated method stub
		//插入订单并返回Id
		 orderDao.insertOrder(orderParmMap);
		 //通过OrderNo 查找到唯一的订单id
		 Integer orderId= orderDao.selectIdByOrderNo(orderParmMap);
		 return orderId;
	}
	/**通过userId和订单状态查找集合*/
	public List<Map> findOrderInfoByOrderStatusAndUserId(Map parmMap) {
		// TODO Auto-generated method stub
		//通过userId和订单状态查找集合
		//没进行合并处理的order
		List<Map> disorderList= orderDao.findOrderInfoByOrderStatusAndUserId(parmMap);
		return disorderList;
	}
	/**软删除订单*/
	public void updateDelFlagOrderByOrderId(Map parmMap) {
		// TODO Auto-generated method stub
		orderDao.updateDelFlagOrderByOrderId(parmMap);
	}

}
