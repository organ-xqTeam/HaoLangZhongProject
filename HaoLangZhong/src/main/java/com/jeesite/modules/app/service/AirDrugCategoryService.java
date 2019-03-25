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
import com.jeesite.modules.app.entity.AirDrugCategory;
import com.jeesite.modules.app.dao.AirDrugCategoryDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * sys_air_drug_categoryService
 * @author 范耘诚
 * @version 2019-03-05
 */
@Service
@Transactional(readOnly=true)
public class AirDrugCategoryService extends CrudService<AirDrugCategoryDao, AirDrugCategory> {
	
	@Autowired
	AirDrugCategoryDao  airDrugCategoryDao;
	
	
	/**
	 * 获取单条数据
	 * @param airDrugCategory
	 * @return
	 */
	@Override
	public AirDrugCategory get(AirDrugCategory airDrugCategory) {
		return super.get(airDrugCategory);
	}
	
	/**
	 * 查询分页数据
	 * @param airDrugCategory 查询条件
	 * @param airDrugCategory.page 分页对象
	 * @return
	 */
	@Override
	public Page<AirDrugCategory> findPage(AirDrugCategory airDrugCategory) {
		return super.findPage(airDrugCategory);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param airDrugCategory
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(AirDrugCategory airDrugCategory) {
		super.save(airDrugCategory);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(airDrugCategory.getId(), "airDrugCategory_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(airDrugCategory.getId(), "airDrugCategory_file");
	}
	
	/**
	 * 更新状态
	 * @param airDrugCategory
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(AirDrugCategory airDrugCategory) {
		super.updateStatus(airDrugCategory);
	}
	
	/**
	 * 删除数据
	 * @param airDrugCategory
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(AirDrugCategory airDrugCategory) {
		super.delete(airDrugCategory);
	}
	/**
	 * @param airDrugCategory
	 * 搜索中成药(药品类别)分类集合
	 * @return
	 */
	public List<Map> queryList(AirDrugCategory airDrugCategory) {
		// TODO Auto-generated method stub
		return airDrugCategoryDao.queryList(airDrugCategory);
	}
	
}