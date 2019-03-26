/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.dao.DoctorInfoDao;
import com.jeesite.modules.app.entity.DoctorInfo;

/**
 * 医生管理Service
 * @author 李昊翀
 * @version 2019-02-21
 */
@Service
@Transactional(readOnly=true)
public class DoctorInfoService {
	
	@Autowired
	private DoctorInfoDao doctorInfoDao;
	
	/**
	 * 用户端根据医生标签检索医生列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryListByLabel(DoctorInfo requestParams) {
		return doctorInfoDao.queryListByLabel(requestParams);
	}
	
	/**
	 * 用户端根据医生标签检索医生列表count
	 * */
	@Transactional(readOnly=false)
	public int queryCountByLabel(DoctorInfo requestParams) {
		return doctorInfoDao.queryCountByLabel(requestParams);
	}
	
	/**
	 * 用户端多条件查询医生列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryList(DoctorInfo requestParams) {
		return doctorInfoDao.queryList(requestParams);
	}
	
	/**
	 * 用户端多条件查询医生列表count
	 * */
	@Transactional(readOnly=false)
	public int queryCount(DoctorInfo requestParams) {
		return doctorInfoDao.queryCount(requestParams);
	}
	
	/**
	 * 查询医生的详细信息
	 * */
	@Transactional(readOnly=false)
	public JSONObject findOneByDoctorid(String doctorid) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("doctorInfo", doctorInfoDao.findOneByDoctorid(doctorid));
		return resultJson;
	}
	
	/**
	 * 查询医生评论列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryCommentList(DoctorInfo requestParams) {
		return doctorInfoDao.queryCommentList(requestParams);
	}
	
	/**
	 * 查询医生评论列表count
	 * */
	@Transactional(readOnly=false)
	public int queryCommentCount(DoctorInfo requestParams) {
		return doctorInfoDao.queryCommentCount(requestParams);
	}
	
	/**
	 * 查询医生问诊列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryConsultationList(DoctorInfo requestParams) {
		return doctorInfoDao.queryConsultationList(requestParams);
	}
	
	/**
	 * 查询医生问诊列表count
	 * */
	@Transactional(readOnly=false)
	public int queryConsultationCount(DoctorInfo requestParams) {
		return doctorInfoDao.queryConsultationCount(requestParams);
	}

	/**
	 * 查询订单数量
	 * */
	@Transactional(readOnly=false)
	public int findOrderCount(String id) {
		return doctorInfoDao.findOrderCount(id);
	}
	
	/**
	 * 查询医生钱包明细列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryIncomeList(DoctorInfo requestParams) {
		return doctorInfoDao.queryIncomeList(requestParams);
	}
	
	/**
	 * 查询医生钱包明细列表-count
	 * */
	@Transactional(readOnly=false)
	public int queryIncomeCount(DoctorInfo requestParams) {
		return doctorInfoDao.queryIncomeCount(requestParams);
	}
	
	/**
	 * 查询医生钱包余额
	 * */
	@Transactional(readOnly=false)
	public double queryIncomeSum(DoctorInfo requestParams) {
		return doctorInfoDao.queryIncomeSum(requestParams);
	}
	
}