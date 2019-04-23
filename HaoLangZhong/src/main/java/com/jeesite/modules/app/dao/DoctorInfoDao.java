/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.DoctorInfo;

/**
 * 医生管理DAO接口
 * @author 李昊翀
 * @version 2019-02-21
 */
@MyBatisDao
public interface DoctorInfoDao {
	
	List<Map<String, Object>> queryListByLabel(DoctorInfo params);
	
	int queryCountByLabel(DoctorInfo params);
	
	List<Map<String, Object>> queryList(DoctorInfo params);
	
	int queryCount(DoctorInfo params);
	
	Map<String, Object> findOneByDoctorid(@Param("doctorid") String doctorid);
	
	List<Map<String, Object>> queryCommentList(DoctorInfo params);
	
	int queryCommentCount(DoctorInfo params);
	
	List<Map<String, Object>> queryConsultationList(DoctorInfo params);
	
	int queryConsultationCount(DoctorInfo params);
	
	int findOrderCount(@Param("id") String id);
	
	List<Map<String, Object>> queryIncomeList(DoctorInfo params);
	
	int queryIncomeCount(DoctorInfo params);
	
	double queryIncomeSum(DoctorInfo params);

	List<Map<String, Object>> queryOwedList(DoctorInfo requestParams);

	Object queryOwedCount(DoctorInfo requestParams);
	
}