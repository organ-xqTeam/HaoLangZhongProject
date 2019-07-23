/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.PeopleDet;

/**
 * peopleDAO接口
 * @author 范耘诚
 * @version 2019-07-19
 */
@MyBatisDao
public interface PeopleDetDao extends CrudDao<PeopleDet> {
	
}