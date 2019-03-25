package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;
import com.jeesite.common.mybatis.annotation.MyBatisDao;

@MyBatisDao
public interface UserIndexDao {

	List<Map<String, Object>> queryDoctorList();
	
	List<Map<String, Object>> queryBannerList();
	
}
