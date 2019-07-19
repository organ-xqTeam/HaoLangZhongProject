/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.SysOrder;
import com.jeesite.modules.sys.dao.SysOrderDao;

/**
 * sys_orderService
 * @author 范耘诚
 * @version 2019-07-19
 */
@Service
@Transactional(readOnly=true)
public class SysOrderService extends CrudService<SysOrderDao, SysOrder> {
	
	/**
	 * 获取单条数据
	 * @param sysOrder
	 * @return
	 */
	@Override
	public SysOrder get(SysOrder sysOrder) {
		return super.get(sysOrder);
	}
	
	/**
	 * 查询分页数据
	 * @param sysOrder 查询条件
	 * @param sysOrder.page 分页对象
	 * @return
	 */
	@Override
	public Page<SysOrder> findPage(SysOrder sysOrder) {
		return super.findPage(sysOrder);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param sysOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SysOrder sysOrder) {
		super.save(sysOrder);
	}
	
	/**
	 * 更新状态
	 * @param sysOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SysOrder sysOrder) {
		super.updateStatus(sysOrder);
	}
	
	/**
	 * 删除数据
	 * @param sysOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SysOrder sysOrder) {
		super.delete(sysOrder);
	}
	
}