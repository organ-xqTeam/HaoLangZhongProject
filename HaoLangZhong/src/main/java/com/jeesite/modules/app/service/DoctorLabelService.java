package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.modules.app.dao.DoctorLabelDao;

/**
 * 医生标签管理Service
 * @author 李昊翀
 * @version 2019-02-21
 */
@Service
@Transactional(readOnly=true)
public class DoctorLabelService {

	@Autowired
	private DoctorLabelDao doctorLabelDao;
	
	/**
	 * 获取医生标签列表（不分页）
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryList(String type) {
		return doctorLabelDao.queryList(type);
	}
	
	/**
	 * 获取用户咨询时选择的标签列表（不分页）
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryListByIds(List<String> ids) {
		return doctorLabelDao.queryListByIds(ids);
	}
	
}
