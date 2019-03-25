package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.jeesite.common.mybatis.annotation.MyBatisDao;

@MyBatisDao
public interface ConsultationDao {

	/**
	 * 保存用户的咨询信息
	 * */
	int saveConsultation(Map<String, Object> params);
	
	/**
	 * 保存用户的咨询图片
	 * */
	int saveConsultationPic(List<Map<String, Object>> params);
	
	Map<String, Object> findConsultationById(@Param("id") String id);
	
	List<Map<String, Object>> findConsultationPic(@Param("id") String id);
	
}
