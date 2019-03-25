/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.UserShare;

/**
 * 用户分享表DAO接口
 * @author 范耘诚
 * @version 2019-03-15
 */
@MyBatisDao
public interface UserShareDao extends CrudDao<UserShare> {
	/**插入新的分享数据*/
	void insertUserShareMap(Map<String, Object> paramMap);
	
}