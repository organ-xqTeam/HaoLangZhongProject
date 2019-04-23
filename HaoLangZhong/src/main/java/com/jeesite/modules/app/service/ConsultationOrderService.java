package com.jeesite.modules.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.modules.app.dao.ConsultationDao;
import com.jeesite.modules.app.dao.ConsultationOrderDao;
import com.jeesite.modules.app.entity.Consultation;
import com.jeesite.modules.app.utils.DateUtil;
import com.jeesite.modules.app.utils.OrderCodeFactory;

/**
 * 咨询订单管理Service
 * @author 李昊翀
 * @version 2019-02-28
 */
@Service
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
@SuppressWarnings("unchecked")
public class ConsultationOrderService {
	
	@Autowired
	private ConsultationDao consultationDao;
	@Autowired
	private ConsultationOrderDao consultationOrderDao;
	
	/**
	 * 保存用户咨询订单
	 * */
	@Transactional(readOnly=false)
	public Map<String, Object> saveConsultationOrder(Map<String, Object> requestModel) {
		String time = DateUtil.getSysTime1();
		requestModel.put("create_date", time);
		requestModel.put("del_flag", "0");
		Object create_dateObj= requestModel.get("create_by");
		String userId=null;
		if(create_dateObj!=null) {
			userId=(String) create_dateObj;
			requestModel.put("userId", userId);
		}
		consultationDao.saveConsultation(requestModel);
		// 处理咨询图片
		List<String> picArray = null;
		List<Map<String, Object>> picList = new ArrayList<Map<String, Object>>();
		if (requestModel.containsKey("pic") && requestModel.get("pic") instanceof List) {
			picArray = (List<String>) requestModel.get("pic");
			for(String picid : picArray) {
				Map<String, Object> pic = new HashMap<String, Object>();
				pic.put("consultationid", requestModel.get("id"));
				pic.put("pic", picid);
				pic.put("create_by", requestModel.get("create_by"));
				pic.put("create_date", time);
				pic.put("del_flag", "0");
				picList.add(pic);
			}
			if (picArray.size() > 0) {
				consultationDao.saveConsultationPic(picList);
				requestModel.put("pics", picList);
			}
		}
		// 处理咨询订单
		Map<String, Object> order = new HashMap<String, Object>();
		order.put("num", OrderCodeFactory.getOrderCode(1L));
		order.put("doctorid", requestModel.get("doctorid"));
		order.put("consultationid", requestModel.get("id"));
		order.put("orderstate", "1");
		order.put("paytype", "4");
		order.put("amount", requestModel.get("amount"));
		order.put("create_by", requestModel.get("create_by"));
		order.put("create_date", time);
		order.put("del_flag", "0");		
		consultationOrderDao.saveConsultationOrder(order);
		requestModel.put("orderInfo", order);
		return requestModel;
	}
	
	/**
	 * 修改用户咨询订单
	 * */
	@Transactional(readOnly=false)
	public void updateConsultationOrder(Map<String, Object> requestModel) {
		requestModel.put("update_date", DateUtil.getSysTime1());
		consultationOrderDao.updateConsultationOrder(requestModel);
	}
	
	/**
	 * 查询用户的问诊列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryList(Consultation c) {
		return consultationOrderDao.queryList(c);
	}
	
	/**
	 * 查询用户的问诊列表-count
	 * */
	@Transactional(readOnly=false)
	public int queryCount(Consultation c) {
		return consultationOrderDao.queryCount(c);
	}
	
	/**
	 * 医生回复订单
	 * */
	@Transactional(readOnly=false)
	public void reply(Map<String, Object> requestModel) {
		Map<String, Object> replyMap = new HashMap<String, Object>();
		Map<String, Object> orderMap = new HashMap<String, Object>();
		replyMap.put("id", requestModel.get("consultationid").toString());
		replyMap.put("reply", requestModel.get("reply").toString());
		replyMap.put("replytime", DateUtil.getSysTime1());
		consultationOrderDao.reply(replyMap);
		orderMap.put("id", requestModel.get("orderid").toString());
		orderMap.put("orderstate", "7");
		consultationOrderDao.updateConsultationOrder(orderMap);
	}
	/**通过主键查找咨询订单*/
	public Map<String, Object> getConsultationOrderByOrderId(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		return consultationOrderDao.getConsultationOrderByOrderId(requestMap);
	}

	/**更新成已支付状态*/
	public void updateOrderPay(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		/**
		 * <if test="orderstate != null">orderstate=#{orderstate},</if>
			<if test="update_by != null">update_by=#{update_by},</if>
			<if test="update_date != null">update_date=#{update_date}</if>
		 * 
		 */
		requestMap.put("orderstate", "2");
		requestMap.put("update_date", new Date());
		//先默认微信支付
		requestMap.put("paytype", "1");
		consultationOrderDao.updateOrderPay(requestMap);
	}
	
}
