package com.jeesite.modules.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.DiscountDao;
import com.jeesite.modules.app.entity.Discount;
import com.jeesite.modules.app.entity.Order;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
@Service
public class DiscountService extends CrudService<DiscountDao, Discount> {
	@Autowired
	DiscountDao discountDao;

	public Map selectByUserId(Map parmMap) {
		// TODO Auto-generated method stub
		return discountDao.selectByUserId(parmMap);
	}

	public void insertDiscount(Map parmMap) {
		discountDao.insertDiscount(parmMap);
	}

	/** 购买成功处理红包金额的操作*/
	public void handlePayDiscount(Order order) {
		// TODO Auto-generated method stub
		Discount discount = new Discount();
		discount.setUserId(order.getUserId());
		discount = discountDao.findList(discount).get(0);
		Integer discountPricePay = Integer.valueOf(order.getDiscountPrice());
		Integer yuanDiscountPrice = Integer.valueOf(discount.getDiscountPrice());
		if (yuanDiscountPrice >= discountPricePay) {
			yuanDiscountPrice = yuanDiscountPrice - discountPricePay;
		} 
		discount.setDiscountPrice(yuanDiscountPrice.toString());
		discountDao.update(discount);

	}

}
