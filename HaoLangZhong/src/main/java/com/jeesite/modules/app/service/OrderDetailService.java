package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.OrderDetailDao;
import com.jeesite.modules.app.entity.OrderDetail;


/**
 * 
 * @author 范耘诚
 * 2019.3.13
 *
 */
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
@Service
public class OrderDetailService  extends CrudService<OrderDetailDao,OrderDetail> {
	@Autowired
	private OrderDetailDao orderDetailDao;
	/**批量增加订单吗详情表*/
	public void insertOrderDetailAll(List<Map> orderDetailMapList) {
		// TODO Auto-generated method stub
		//批量增加订单吗详情表
		for (int i = 0; i < orderDetailMapList.size(); i++) {
			orderDetailDao.insertOrderDetail(orderDetailMapList.get(i));
			
			
		}
		
	}


}
