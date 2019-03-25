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
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
@Service
public class DiscountService  extends CrudService<DiscountDao, Discount>  {
	@Autowired
	DiscountDao discountDao;
	public Map selectByUserId(Map parmMap) {
		// TODO Auto-generated method stub
		return null;
	}
	public void insertDiscount(Map parmMap) {
		discountDao.insertDiscount(parmMap);
	}

}
