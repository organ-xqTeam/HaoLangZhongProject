package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.modules.app.dao.ConsultationDao;

/**
 * 用户咨询管理
 * */
@Service
@Transactional(readOnly=true)
public class ConsultationService {

	@Autowired
	private ConsultationDao consultationDao;
	
	/**
	 * 获取咨询信息详情
	 * */
	@Transactional(readOnly=false)
	public Map<String, Object> findConsultationById(String id) {
		return consultationDao.findConsultationById(id);
	}
	
	/**
	 * 获取咨询时上传的图片
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> findConsultationPic(String id) {
		return consultationDao.findConsultationPic(id);
	}
	
}
