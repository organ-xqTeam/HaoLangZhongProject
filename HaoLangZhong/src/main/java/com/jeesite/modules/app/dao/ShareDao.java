/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.Share;

/**
 * sys_shareDAO接口
 * @author 范耘诚
 * @version 2019-06-17
 */
@MyBatisDao
public interface ShareDao extends CrudDao<Share> {
	
}