/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.AirShopCartRelation;

/**
 * 购物车关系表DAO接口
 * @author 范耘诚
 * @version 2019-03-05
 */
@MyBatisDao
public interface AirShopCartRelationDao extends CrudDao<AirShopCartRelation> {
	
}