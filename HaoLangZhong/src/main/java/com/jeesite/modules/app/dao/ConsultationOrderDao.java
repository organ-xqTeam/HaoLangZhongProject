package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.Consultation;

@MyBatisDao
public interface ConsultationOrderDao {

	/**
	 * 保存用户咨询订单
	 * */
	int saveConsultationOrder(Map<String, Object> params);
	
	/**
	 * 修改用户咨询订单
	 * */
	int updateConsultationOrder(Map<String, Object> params);
	
	/**
	 * 查询用户的问诊列表
	 * */
	List<Map<String, Object>> queryList(Consultation c);
	
	/**
	 * 查询用户的问诊列表-count
	 * */
	int queryCount(Consultation c);
	
	/**
	 * 医生回复订单
	 * */
	int reply(Map<String, Object> params);
	/**
	 * 通过主键查找咨询订单
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> getConsultationOrderByOrderId(Map<String, Object> requestMap);

	/**
	 * 更新成已支付状态
	 * @param requestMap
	 * @return
	 */
	void updateOrderPay(Map<String, Object> requestMap);
	
}
