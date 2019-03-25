package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.modules.app.dao.UserIndexDao;

/**
 * 首页信息Service
 * @author 李昊翀
 * @version 2019-03-11
 */
@Service
@Transactional(readOnly=true)
public class UserIndexService {

	@Autowired
	private UserIndexDao userIndexDao;
	
	/**
	 * 获取推荐医生列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryDoctorList() {
		return userIndexDao.queryDoctorList();
	}
	
	/**
	 * 获取banner图列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryBannerList() {
		return userIndexDao.queryBannerList();
	}
	
}
