package com.jeesite.modules.app.dao;

import java.util.Map;

import com.jeesite.common.mybatis.annotation.MyBatisDao;

/**
 * 医生锦旗管理DAO接口
 * @author 李昊翀
 * @version 2019-03-14
 */
@MyBatisDao
public interface DoctorGiftOrderDao {

	int sendGift(Map<String, Object> params);
	
	int updateGiftOrder(Map<String, Object> params);
	
}
