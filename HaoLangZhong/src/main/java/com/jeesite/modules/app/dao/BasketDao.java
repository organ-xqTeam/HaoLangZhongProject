/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;
import java.util.List;
import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.Basket;

/**
 * sys_basketDAO接口
 * @author 范耘诚
 * @version 2019-03-11
 */
@MyBatisDao
public interface BasketDao extends CrudDao<Basket> {
	/**通过userId得到用户的购物车列表*/
	List<Map> getBasketByUserId(Map userMap);
	/**如果为空查询次UserId是否有购物车表*/
	Long selectCountBasketByUserId(Map userMap);
	/**通过userid插入一条购物车数据*/
	void insertBasketByUserId(Map userMap);
	/**插入购物商品关系表*/
	void insertBasketDrugByUserIdAndDrudId(Map parmMap);
	/**删除购物车商品的操作*/
	void delFlagDrugBasketByDrugId(Map parmMap);
	
}