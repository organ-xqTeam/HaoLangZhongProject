package com.jeesite.modules.app.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.modules.app.dao.DoctorGiftOrderDao;
import com.jeesite.modules.app.utils.DateUtil;

/**
 * 医生锦旗管理Service
 * @author 李昊翀
 * @version 2019-02-21
 */
@Service
@Transactional(readOnly=true)
public class DoctorGiftOrderService {

	@Autowired
	private DoctorGiftOrderDao doctorGiftOrderDao;
	
	/**
	 * 赠送锦旗
	 * */
	@Transactional(readOnly=false)
	public int sendGift(Map<String, Object> requestParams) {
		requestParams.put("create_date", DateUtil.getSysTime1());
		requestParams.put("del_flag", "0");
		return doctorGiftOrderDao.sendGift(requestParams);
	}
	
	/**
	 * 修改订单状态
	 * */
	@Transactional(readOnly=false)
	public int updateGiftOrder(Map<String, Object> requestParams) {
		requestParams.put("update_date", DateUtil.getSysTime1());
		return doctorGiftOrderDao.updateGiftOrder(requestParams);
	}
	
}
