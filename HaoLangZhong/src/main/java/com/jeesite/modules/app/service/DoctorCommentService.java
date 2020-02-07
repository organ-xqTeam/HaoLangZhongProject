package com.jeesite.modules.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.modules.app.dao.DoctorCommentDao;
import com.jeesite.modules.app.utils.DateUtil;

/**
 * 医生评论管理Service
 * @author 李昊翀
 * @version 2019-02-21
 */
@Service
@Transactional(readOnly=true)
public class DoctorCommentService {

	@Autowired
	private DoctorCommentDao doctorCommentDao;
	
	/**
	 * 提交评论
	 * */
	@Transactional(readOnly=false)
	public int saveDoctorComment(Map<String, Object> params) {
		params.put("create_date", DateUtil.getSysTime1());
		params.put("del_flag", "0");
		return doctorCommentDao.saveDoctorComment(params);
	}
	
}
