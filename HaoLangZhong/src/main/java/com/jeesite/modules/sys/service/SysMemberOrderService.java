/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.SysMemberOrder;
import com.jeesite.modules.sys.dao.SysMemberOrderDao;

/**
 * sys_member_orderService
 * @author 范耘诚
 * @version 2019-07-22
 */
@Service
@Transactional(readOnly=true)
public class SysMemberOrderService extends CrudService<SysMemberOrderDao, SysMemberOrder> {
	
	/**
	 * 获取单条数据
	 * @param sysMemberOrder
	 * @return
	 */
	@Override
	public SysMemberOrder get(SysMemberOrder sysMemberOrder) {
		return super.get(sysMemberOrder);
	}
	
	/**
	 * 查询分页数据
	 * @param sysMemberOrder 查询条件
	 * @param sysMemberOrder.page 分页对象
	 * @return
	 */
	@Override
	public Page<SysMemberOrder> findPage(SysMemberOrder sysMemberOrder) {
		return super.findPage(sysMemberOrder);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param sysMemberOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SysMemberOrder sysMemberOrder) {
		super.save(sysMemberOrder);
	}
	
	/**
	 * 更新状态
	 * @param sysMemberOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SysMemberOrder sysMemberOrder) {
		super.updateStatus(sysMemberOrder);
	}
	
	/**
	 * 删除数据
	 * @param sysMemberOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SysMemberOrder sysMemberOrder) {
		super.delete(sysMemberOrder);
	}
	
}