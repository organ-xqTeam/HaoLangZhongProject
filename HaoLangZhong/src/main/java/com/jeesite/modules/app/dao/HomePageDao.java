/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.HomePage;

/**
 * sys_home_pageDAO接口
 * @author 范耘诚
 * @version 2019-04-15
 */
@MyBatisDao
public interface HomePageDao extends CrudDao<HomePage> {
	
}