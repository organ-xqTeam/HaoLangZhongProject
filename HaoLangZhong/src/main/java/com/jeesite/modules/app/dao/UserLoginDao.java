package com.jeesite.modules.app.dao;

import java.util.Map;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.User;

@MyBatisDao
public interface UserLoginDao {
	
	int registerCheck(String mobile);

	int register(Map<String, Object> params);
	
	Map<String, Object> getUserInfo(User u);
	
	int updateUser(Map<String, Object> params);
	
}
