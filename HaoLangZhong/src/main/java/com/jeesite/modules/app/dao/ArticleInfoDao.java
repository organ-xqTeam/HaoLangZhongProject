package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.ArticleInfo;

@MyBatisDao
public interface ArticleInfoDao {

	List<Map<String, Object>> queryList(ArticleInfo params);
	
	int queryCount(ArticleInfo params);
	
	Map<String, Object> findOneById(@Param("id") String id);
	
}
