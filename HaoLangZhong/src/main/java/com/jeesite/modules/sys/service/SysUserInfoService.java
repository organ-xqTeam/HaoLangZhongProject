/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.dao.SysUserInfoDao;
import com.jeesite.modules.sys.entity.SysUserInfo;



/**
 * sys_user_infoService
 * @author a
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=false)
public class SysUserInfoService   extends CrudService<SysUserInfoDao,SysUserInfo>{
	
	@Autowired
	private SysUserInfoDao sysUserInfoDao;
	
	/**
	 * 获取单条数据
	 * @param sysUserInfo
	 * @return
	 */
//	@Override
	public SysUserInfo get(SysUserInfo sysUserInfo) {
//		return super.get(sysUserInfo);
		return sysUserInfoDao.get(sysUserInfo);
	}
	
	/**
	 * 查询医生用户的分页数据
	 * @param sysUserInfo 查询条件
	 * @param sysUserInfo.page 分页对象
	 * @return
	 */
/*	public void findPage(SysUserInfo sysUserInfo, Page<SysUserInfo> page) {
		page.setList(sysUserInfoDao.queryList(sysUserInfo));
		page.setCount(sysUserInfoDao.queryCount(sysUserInfo));
	}*/
	/**
	  * 查询普通用户的分页
	 * @param sysUserInfo 查询条件
	 * @param sysUserInfo.page 分页对象
	 * @return
	 */
	public void findCommonPage(SysUserInfo sysUserInfo, Page<SysUserInfo> page) {
		page.setList(sysUserInfoDao.findList(sysUserInfo));
		page.setCount(sysUserInfoDao.findCount(sysUserInfo));
	}

	
	/**
	 * 保存数据（插入或更新）
	 * @param sysUserInfo
	 */
//	@Override
	@Transactional(readOnly=false)
	public void save(SysUserInfo sysUserInfo) {
//		super.save(sysUserInfo);
	}
	
	/**
	 * 更新状态
	 * @param sysUserInfo
	 */
//	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SysUserInfo sysUserInfo) {
//		super.updateStatus(sysUserInfo);
	}
	
	/**
	 * 删除数据
	 * @param sysUserInfo
	 */
	@Transactional(readOnly=false)
	public void delete(String id) {
		sysUserInfoDao.deleteDoctor(id);
	}
	
}