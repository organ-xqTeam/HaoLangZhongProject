/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.SysMemberOrder;

/**
 * sys_member_orderDAO接口
 * @author 范耘诚
 * @version 2019-07-22
 */
@MyBatisDao
public interface SysMemberOrderDao extends CrudDao<SysMemberOrder> {
	
}