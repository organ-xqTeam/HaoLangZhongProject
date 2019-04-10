/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.SysAirDrug;
import com.jeesite.modules.sys.dao.SysAirDrugDao;

/**
 * sys_air_drugService
 * @author 范耘诚
 * @version 2019-04-09
 */
@Service
@Transactional(readOnly=true)
public class SysAirDrugService extends CrudService<SysAirDrugDao, SysAirDrug> {
	
	/**
	 * 获取单条数据
	 * @param sysAirDrug
	 * @return
	 */
	@Override
	public SysAirDrug get(SysAirDrug sysAirDrug) {
		return super.get(sysAirDrug);
	}
	
	/**
	 * 查询分页数据
	 * @param sysAirDrug 查询条件
	 * @param sysAirDrug.page 分页对象
	 * @return
	 */
	@Override
	public Page<SysAirDrug> findPage(SysAirDrug sysAirDrug) {
		return super.findPage(sysAirDrug);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param sysAirDrug
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SysAirDrug sysAirDrug) {
		super.save(sysAirDrug);
	}
	
	/**
	 * 更新状态
	 * @param sysAirDrug
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SysAirDrug sysAirDrug) {
		super.updateStatus(sysAirDrug);
	}
	
	/**
	 * 删除数据
	 * @param sysAirDrug
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SysAirDrug sysAirDrug) {
		super.delete(sysAirDrug);
	}
	
}