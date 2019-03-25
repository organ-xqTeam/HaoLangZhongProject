/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.entity.DiscountDetail;
import com.jeesite.modules.app.dao.DiscountDetailDao;

/**
 * sys_discount_detailService
 * @author 范耘诚
 * @version 2019-03-15
 */
@Service
@Transactional(readOnly=true)
public class DiscountDetailService extends CrudService<DiscountDetailDao, DiscountDetail> {
	@Autowired
	private DiscountDetailDao discountDetailDao;
	
	/**
	 * 获取单条数据
	 * @param discountDetail
	 * @return
	 */
	@Override
	public DiscountDetail get(DiscountDetail discountDetail) {
		return super.get(discountDetail);
	}
	
	/**
	 * 查询分页数据
	 * @param discountDetail 查询条件
	 * @param discountDetail.page 分页对象
	 * @return
	 */
	@Override
	public Page<DiscountDetail> findPage(DiscountDetail discountDetail) {
		return super.findPage(discountDetail);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param discountDetail
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DiscountDetail discountDetail) {
		super.save(discountDetail);
	}
	
	/**
	 * 更新状态
	 * @param discountDetail
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DiscountDetail discountDetail) {
		super.updateStatus(discountDetail);
	}
	
	/**
	 * 删除数据
	 * @param discountDetail
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DiscountDetail discountDetail) {
		super.delete(discountDetail);
	}
	/**增加折扣从详情表的操作*/
	public void insertDiscountDetailMap(Map discountDetailMap) {
		// TODO Auto-generated method stub
		discountDetailDao.insertDiscountDetailMap(discountDetailMap);
	}
	
}