package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.mybatis.annotation.MyBatisDao;

@MyBatisDao
public interface DoctorLabelDao {

	List<Map<String, Object>> queryList(String type);
	
	List<Map<String, Object>> queryListByIds(@Param("ids") List<String> ids);
	
	void insert (Map<String, Object> map);
	
}
