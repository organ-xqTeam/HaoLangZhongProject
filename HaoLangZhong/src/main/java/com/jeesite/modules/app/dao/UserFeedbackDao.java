package com.jeesite.modules.app.dao;

import java.util.Map;
import com.jeesite.common.mybatis.annotation.MyBatisDao;

@MyBatisDao
public interface UserFeedbackDao {

	int saveFeedback(Map<String, Object> params);
	
}
