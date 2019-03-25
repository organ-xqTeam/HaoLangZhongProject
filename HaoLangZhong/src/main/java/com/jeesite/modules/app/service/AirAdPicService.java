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
import com.jeesite.modules.app.entity.AirAdPic;
import com.jeesite.modules.app.dao.AirAdPicDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * sys_air_ad_picService
 * @author 范耘诚
 * @version 2019-03-05
 */
@Service
@Transactional(readOnly=true)
public class AirAdPicService extends CrudService<AirAdPicDao, AirAdPic> {
	@Autowired
	private AirAdPicDao airAdPicDao;
	/**
	 * 获取单条数据
	 * @param airAdPic
	 * @return
	 */
	@Override
	public AirAdPic get(AirAdPic airAdPic) {
		return super.get(airAdPic);
	}
	
	/**
	 * 查询分页数据
	 * @param airAdPic 查询条件
	 * @param airAdPic.page 分页对象
	 * @return
	 */
	@Override
	public Page<AirAdPic> findPage(AirAdPic airAdPic) {
		return super.findPage(airAdPic);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param airAdPic
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(AirAdPic airAdPic) {
		super.save(airAdPic);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(airAdPic.getId(), "airAdPic_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(airAdPic.getId(), "airAdPic_file");
	}
	
	/**
	 * 更新状态
	 * @param airAdPic
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(AirAdPic airAdPic) {
		super.updateStatus(airAdPic);
	}
	
	/**
	 * 删除数据
	 * @param airAdPic
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(AirAdPic airAdPic) {
		super.delete(airAdPic);
	}
	
	public List<Map> queryList(AirAdPic airAdPic){
		return airAdPicDao.queryList(airAdPic);
	}
	
	
}