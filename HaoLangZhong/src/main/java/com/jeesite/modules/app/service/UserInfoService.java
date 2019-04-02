package com.jeesite.modules.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.dao.UserInfoDao;
import com.jeesite.modules.app.utils.DateUtil;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.BasketDao;
import com.jeesite.modules.app.dao.UserInfoDao;
import com.jeesite.modules.app.entity.Basket;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.utils.DateUtil;

/**
 * 用户信息管理Service
 * @author 李昊翀
 * @version 2019-02-21
 */
@Service
@Transactional(readOnly=true)
@SuppressWarnings("unchecked")
public class UserInfoService extends CrudService<UserInfoDao, UserInfo> {

	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
    	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 保存用户信息
	 * */
	@Transactional(readOnly=false)
	public Map<String, Object> saveUserInfo(Map<String, Object> requestMap) {
		userInfoDao.saveUserInfo(requestMap);
		String uid = requestMap.get("id").toString();
		Map<String, Object> user = userInfoDao.findUserInfo(uid);
		stringRedisTemplate.opsForValue().set(requestMap.get("token").toString(), user.toString());
		user.put("token", requestMap.get("token").toString());
		return user;
	}
	
	/**
	 * 保存身份验证信息
	 * */
	@Transactional(readOnly=false)
	public int saveValidate(Map<String, Object> requestMap) {
		String time = DateUtil.getSysTime1();
		Map<String, Object> doctorInfo = new HashMap<String, Object>();
		Map<String, Object> doctorPic = new HashMap<String, Object>();
		doctorInfo.put("doctorid", requestMap.get("id").toString());
		doctorInfo.put("name", requestMap.get("name").toString());
		doctorInfo.put("telephone", requestMap.get("telephone").toString());
		doctorInfo.put("idcard", requestMap.get("idcard").toString());
		doctorInfo.put("adress", requestMap.get("adress").toString());
		doctorInfo.put("create_date", time);
		doctorInfo.put("del_flag", "0");
		userInfoDao.saveDoctorInfo(doctorInfo);
		doctorPic.put("doctorid", requestMap.get("id").toString());
		doctorPic.put("certificate1", requestMap.get("certificate1").toString());
		doctorPic.put("certificate2", requestMap.get("certificate2").toString());
		doctorPic.put("certificate3", requestMap.get("certificate3").toString());
		doctorPic.put("create_date", time);
		doctorPic.put("del_flag", "0");
		userInfoDao.saveDoctorPic(doctorPic);
		return 1;
	}
	
	/**
	 * 编辑医生个人信息
	 * */
	@Transactional(readOnly=false)
	public void saveDoctorInfo(Map<String, Object> requestMap) {
		if (requestMap.containsKey("icon")) {
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("icon", requestMap.get("icon").toString());
			userMap.put("id", requestMap.get("doctorid").toString());
			userInfoDao.saveUserInfo(userMap);
		}
		if (requestMap.containsKey("technical") || requestMap.containsKey("classify") || requestMap.containsKey("introduce")) {
			Map<String, Object> doctorMap = new HashMap<String, Object>();
			doctorMap.put("technical", requestMap.get("technical").toString());
			doctorMap.put("classify", requestMap.get("classify").toString());
			doctorMap.put("introduce", requestMap.get("introduce").toString());
			doctorMap.put("doctorid", requestMap.get("doctorid").toString());
			userInfoDao.updateDoctorInfo(doctorMap);
		}
		if (requestMap.containsKey("lable") && requestMap.get("lable") instanceof List) {
			List<String> lable = new ArrayList<String>();
			lable = (List<String>) requestMap.get("lable");
			List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
			for(String l : lable) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("labelid", l);
				item.put("doctorid", requestMap.get("doctorid").toString());
				item.put("create_date", DateUtil.getSysTime1());
				item.put("del_flag", "0");
				itemList.add(item);
			}
			userInfoDao.deleteDoctorLable(requestMap.get("doctorid").toString());
			userInfoDao.saveDoctorLable(itemList);
		}
		if (requestMap.containsKey("introducepic1") ||
				requestMap.containsKey("introducepic2") || 
					requestMap.containsKey("introducepic3") ||
						requestMap.containsKey("introducevideo")) {
			Map<String, Object> picMap = new HashMap<String, Object>();
			picMap.put("doctorid", requestMap.get("doctorid").toString());
			picMap.put("introducepic1", requestMap.get("introducepic1").toString());
			picMap.put("introducepic2", requestMap.get("introducepic2").toString());
			picMap.put("introducepic3", requestMap.get("introducepic3").toString());
			picMap.put("introducevideo", requestMap.get("introducevideo").toString());
			userInfoDao.updateDoctorPic(picMap);
		}
	}
	
	/**
	 * 获取医生个人信息
	 * */
	@Transactional(readOnly=false)
	public JSONObject findDoctorInfo(String id) {
		JSONObject result = new JSONObject();
		result.put("doctorInfo", userInfoDao.findDoctorInfo(id));
		result.put("doctorPic", userInfoDao.findDoctorPic(id));
		return result;
	}
	
}
