/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.PublicDiscuss;

/**
 * sys_public_discussDAO接口
 * @author 范耘诚
 * @version 2019-04-24
 */
@MyBatisDao
public interface PublicDiscussDao extends CrudDao<PublicDiscuss> {
	
}