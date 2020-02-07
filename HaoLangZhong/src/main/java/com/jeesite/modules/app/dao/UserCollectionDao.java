package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.UserCollection;

@MyBatisDao
public interface UserCollectionDao {

	List<Map<String, Object>> queryDoctorList(UserCollection u);
	
	int queryDoctorCount(UserCollection u);
	
	List<Map<String, Object>> queryArticleList(UserCollection u);
	
	int queryArticleCount(UserCollection u);
	
	int saveUserCollection(Map<String, Object> params);
	
	int cancelCollection(@Param("userid") String userid, @Param("collectionid") String collectionid);
	
}
