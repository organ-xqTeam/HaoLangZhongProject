package com.jeesite.modules.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.BasketDao;
import com.jeesite.modules.app.dao.DashangOrderDao;
import com.jeesite.modules.app.entity.Basket;
import com.jeesite.modules.app.entity.DashangOrder;

@Service
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)

public class DashangOrderService extends CrudService<DashangOrderDao, DashangOrder> {

}
