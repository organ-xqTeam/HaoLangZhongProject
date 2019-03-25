package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.BasketDao;
import com.jeesite.modules.app.entity.Basket;

@Service
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
public class BasketService extends CrudService<BasketDao, Basket> {
	
	
	@Autowired
	private BasketDao  basketDao;
	/**通过userId得到用户的购物车列表*/
	@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
	public List<Map> getBasketByUserId(Map userMap) {
		// TODO Auto-generated method stub
		 List<Map> basketInfoList=basketDao.getBasketByUserId(userMap);
		 if(basketInfoList==null||basketInfoList.size()==0) {
			 //如果为空查询次UserId是否有购物车表
			 Long countL=basketDao.selectCountBasketByUserId(userMap);
			 if(countL==0) {
				 //通过userid插入一条购物车数据
				 basketDao.insertBasketByUserId(userMap);
			 }
			 
		 }
		 return basketInfoList;
	}
	@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
	public void addDrugBasket(Map parmMap) {
		 List<Map> basketInfoList=basketDao.getBasketByUserId(parmMap);
		 if(basketInfoList==null||basketInfoList.size()==0) {
			 //如果为空查询次UserId是否有购物车表
			 Long countL=basketDao.selectCountBasketByUserId(parmMap);
			 if(countL==0) {
				 //通过userid插入一条购物车数据
				 basketDao.insertBasketByUserId(parmMap);
			 }
			 
		 }else {
			 /**插入购物商品关系表*/
			 basketDao.insertBasketDrugByUserIdAndDrudId(parmMap);
		 }
		
	}
	/**删除购物车商品的操作*/
	public void delFlagDrugBasketByDrugId(Map parmMap) {
		// TODO Auto-generated method stub
		basketDao.delFlagDrugBasketByDrugId(parmMap);
		
	}
	
	
	
	

}
