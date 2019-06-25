/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.AppVersion;

/**
 * sys_app_versionDAO接口
 * @author 范耘诚
 * @version 2019-05-29
 */
@MyBatisDao
public interface AppVersionDao extends CrudDao<AppVersion> {
	
}