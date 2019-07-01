package com.jeesite.modules.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.DiscountDao;
import com.jeesite.modules.app.dao.TiaoliOrderDao;
import com.jeesite.modules.app.entity.Discount;
import com.jeesite.modules.app.entity.TiaoliOrder;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
@Service
public class TiaoliOrderService extends CrudService<TiaoliOrderDao, TiaoliOrder> {

}
