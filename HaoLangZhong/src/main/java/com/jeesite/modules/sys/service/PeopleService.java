/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.People;
import com.jeesite.modules.sys.dao.PeopleDao;
import com.jeesite.modules.sys.entity.PeopleDet;
import com.jeesite.modules.sys.dao.PeopleDetDao;

/**
 * peopleService
 * @author 范耘诚
 * @version 2019-07-19
 */
@Service
@Transactional(readOnly=true)
public class PeopleService extends CrudService<PeopleDao, People> {
	
	@Autowired
	private PeopleDetDao peopleDetDao;
	
	/**
	 * 获取单条数据
	 * @param people
	 * @return
	 */
	@Override
	public People get(People people) {
		People entity = super.get(people);
		if (entity != null){
			PeopleDet peopleDet = new PeopleDet(entity);
			peopleDet.setStatus(PeopleDet.STATUS_NORMAL);
			entity.setPeopleDetList(peopleDetDao.findList(peopleDet));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param people 查询条件
	 * @param people.page 分页对象
	 * @return
	 */
	@Override
	public Page<People> findPage(People people) {
		return super.findPage(people);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param people
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(People people) {
		super.save(people);
		// 保存 People子表
		for (PeopleDet peopleDet : people.getPeopleDetList()){
			if (!PeopleDet.STATUS_DELETE.equals(peopleDet.getStatus())){
				peopleDet.setPeopleId(people);
				if (peopleDet.getIsNewRecord()){
					peopleDet.preInsert();
					peopleDetDao.insert(peopleDet);
				}else{
					peopleDet.preUpdate();
					peopleDetDao.update(peopleDet);
				}
			}else{
				peopleDetDao.delete(peopleDet);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param people
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(People people) {
		super.updateStatus(people);
	}
	
	/**
	 * 删除数据
	 * @param people
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(People people) {
		super.delete(people);
		PeopleDet peopleDet = new PeopleDet();
		peopleDet.setPeopleId(people);
		peopleDetDao.delete(peopleDet);
	}
	
}