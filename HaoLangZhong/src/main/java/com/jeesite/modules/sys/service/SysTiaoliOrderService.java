/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.SysTiaoliOrder;
import com.jeesite.modules.sys.dao.SysTiaoliOrderDao;

/**
 * sys_tiaoli_orderService
 * @author 范耘诚
 * @version 2019-07-22
 */
@Service
@Transactional(readOnly=true)
public class SysTiaoliOrderService extends CrudService<SysTiaoliOrderDao, SysTiaoliOrder> {
	
	/**
	 * 获取单条数据
	 * @param sysTiaoliOrder
	 * @return
	 */
	@Override
	public SysTiaoliOrder get(SysTiaoliOrder sysTiaoliOrder) {
		return super.get(sysTiaoliOrder);
	}
	
	/**
	 * 查询分页数据
	 * @param sysTiaoliOrder 查询条件
	 * @param sysTiaoliOrder.page 分页对象
	 * @return
	 */
	@Override
	public Page<SysTiaoliOrder> findPage(SysTiaoliOrder sysTiaoliOrder) {
		return super.findPage(sysTiaoliOrder);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param sysTiaoliOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SysTiaoliOrder sysTiaoliOrder) {
		super.save(sysTiaoliOrder);
	}
	
	/**
	 * 更新状态
	 * @param sysTiaoliOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SysTiaoliOrder sysTiaoliOrder) {
		super.updateStatus(sysTiaoliOrder);
	}
	
	/**
	 * 删除数据
	 * @param sysTiaoliOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SysTiaoliOrder sysTiaoliOrder) {
		super.delete(sysTiaoliOrder);
	}
	
}