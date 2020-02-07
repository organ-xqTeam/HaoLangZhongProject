package com.jeesite.modules.app.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.modules.app.dao.DoctorComplaintDao;
import com.jeesite.modules.app.utils.DateUtil;

/**
 * 医生投诉管理Service
 * @author 李昊翀
 * @version 2019-02-21
 */
@Service
@Transactional(readOnly=true)
public class DoctorComplaintService {

	@Autowired
	private DoctorComplaintDao doctorComplaintDao;
	
	/**
	 * 提交投诉
	 * */
	@Transactional(readOnly=false)
	public int saveDoctorComplaint(Map<String, Object> params) {
		params.put("create_date", DateUtil.getSysTime1());
		params.put("del_flag", "0");
		return doctorComplaintDao.saveDoctorComplaint(params);
	}
}
