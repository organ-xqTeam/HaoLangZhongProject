package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.DashangOrder;
import com.jeesite.modules.app.entity.DoctorLabel;

@MyBatisDao
public interface DoctorLabelDao extends CrudDao<DoctorLabel> {

	List<Map<String, Object>> queryList(String type);
	
	List<Map<String, Object>> queryListByIds(@Param("ids") List<String> ids);
	
	void insert (Map<String, Object> map);
	
}
