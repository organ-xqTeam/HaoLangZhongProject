package com.jeesite.modules.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.dao.UserInfoDao;
import com.jeesite.modules.app.utils.DateUtil;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.Application;
import com.jeesite.modules.app.dao.BasketDao;
import com.jeesite.modules.app.dao.DoctorLabelDao;
import com.jeesite.modules.app.dao.DoctorPicDao;
import com.jeesite.modules.app.dao.UserInfoDao;
import com.jeesite.modules.app.entity.Basket;
import com.jeesite.modules.app.entity.DoctorPic;
import com.jeesite.modules.app.entity.PublicDiscuss;
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

public class UserInfoService extends CrudService<UserInfoDao, UserInfo>  {

	
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private DoctorLabelDao doctorLabelDao;
	@Autowired
	private DoctorPicDao doctorPicDao;
	
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
		doctorInfo.put("name", requestMap.get("name").toString());
		
		//通过手机查找
		String telephone = requestMap.get("telephone").toString();
		UserInfo userInfo=new UserInfo();
		userInfo.setMobile(telephone);
		List<UserInfo> userInfoList= userInfoDao.findList(userInfo);
		String doctorid="";
		if(userInfoList!=null&&userInfoList.size()>0) {
			doctorid=userInfoList.get(0).getId();
		}
		doctorInfo.put("doctorid", doctorid);
		if(requestMap.get("telephone")!=null) {
			doctorInfo.put("telephone", requestMap.get("telephone").toString());
		}
		if(requestMap.get("idcard")!=null) {
			doctorInfo.put("idcard", requestMap.get("idcard").toString());
		}
		if(requestMap.get("adress")!=null) {
			doctorInfo.put("adress", requestMap.get("adress").toString());
		}
		doctorInfo.put("create_date", time);
		doctorInfo.put("del_flag", "0");
		userInfoDao.saveDoctorInfo(doctorInfo);
		/*doctorPic.put("doctorid", requestMap.get("id").toString());*/
		if(requestMap.get("certificate1")!=null) {
			doctorPic.put("certificate1", requestMap.get("certificate1").toString());
		}
		if(requestMap.get("saveDoctorPicsaveDoctorPic")!=null) {
			doctorPic.put("certificate2", requestMap.get("certificate2").toString());
		}
		if(requestMap.get("certificate3")!=null) {
			doctorPic.put("certificate3", requestMap.get("certificate3").toString());
		}
		
		doctorPic.put("create_date", time);
		doctorPic.put("del_flag", "0");
		doctorPic.put("doctorid", doctorid);
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
		try {
			Map<String, Object> doctorMap = new HashMap<String, Object>();
			if(requestMap.containsKey("technical")) {
				doctorMap.put("technical", requestMap.get("technical").toString());
			}
			if(requestMap.containsKey("classify")) {
				doctorMap.put("classify", requestMap.get("classify").toString());
			}
			
			if(requestMap.containsKey("introduce")) {
				doctorMap.put("introduce", requestMap.get("introduce").toString());
			}
			if(requestMap.containsKey("doctorid")) {
				doctorMap.put("doctorid", requestMap.get("doctorid").toString());
			}else {
				throw new RuntimeException("doctorid不能为空");
			}
			if(requestMap.containsKey("comeFlag")) {
				doctorMap.put("comeFlag", requestMap.get("comeFlag").toString());
			}
			if(requestMap.containsKey("comeCost")) {
				doctorMap.put("comeCost", requestMap.get("comeCost").toString());
			}
			if(requestMap.containsKey("changeAddress")) {
				doctorMap.put("changeAddress", requestMap.get("changeAddress").toString());
			}

			if(requestMap.containsKey("cityid")) {
				doctorMap.put("cityid", requestMap.get("cityid").toString());
			}
			if(requestMap.containsKey("adress")) {
				doctorMap.put("adress", requestMap.get("adress").toString());
			}
			//------------------------------------------------------------
			if(requestMap.containsKey("agenum")) {
				doctorMap.put("agenum", requestMap.get("agenum").toString());
			}
			if(requestMap.containsKey("professional")) {
				doctorMap.put("professional", requestMap.get("professional").toString());
			}
			if(requestMap.containsKey("workyear")) {
				doctorMap.put("workyear", requestMap.get("workyear").toString());
			}
			/**	 * changeAddress 
			 	*/
			userInfoDao.updateDoctorInfo(doctorMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("更新用户信息失败");
		}
	/*	if (requestMap.containsKey("technical") || requestMap.containsKey("classify") || requestMap.containsKey("introduce")) {
			doctorMap.put("technical", requestMap.get("technical").toString());
			doctorMap.put("classify", requestMap.get("classify").toString());
			doctorMap.put("introduce", requestMap.get("introduce").toString());
			doctorMap.put("doctorid", requestMap.get("doctorid").toString());
			doctorMap.put("cityid", requestMap.get("cityid").toString());
			doctorMap.put("comeFlag", requestMap.get("comeFlag").toString());
			doctorMap.put("comeCost", requestMap.get("comeCost").toString());
			
		}*/
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
			if(itemList.size()>0) {
				userInfoDao.deleteDoctorLable(requestMap.get("doctorid").toString());
				userInfoDao.saveDoctorLable(itemList);
			}
		}
		if (requestMap.containsKey("introducepic1") ||
				requestMap.containsKey("introducepic2") || 
					requestMap.containsKey("introducepic3") ||
						requestMap.containsKey("introducevideo")) {
			DoctorPic doctorPic =new DoctorPic();
			doctorPic.setDoctorid(requestMap.get("doctorid").toString());
			List<DoctorPic> doctorPicList =doctorPicDao.findList(doctorPic);
			if(doctorPicList.size()==0){
				doctorPicDao.insert(doctorPic);
			}
			Map<String, Object> picMap = new HashMap<String, Object>();
			picMap.put("doctorid", requestMap.get("doctorid").toString());
			if(requestMap.get("introducepic1")!=null) {
				picMap.put("introducepic1", requestMap.get("introducepic1").toString());
				picMap.put("certificate1", requestMap.get("introducepic1").toString());
			}
			if(requestMap.get("introducepic2")!=null) {
				picMap.put("introducepic2", requestMap.get("introducepic2").toString());
				picMap.put("certificate2", requestMap.get("introducepic2").toString());
			}
			if(requestMap.get("introducepic3")!=null) {
				picMap.put("introducepic3", requestMap.get("introducepic3").toString());
				picMap.put("certificate3", requestMap.get("introducepic3").toString());
			}
			if(requestMap.get("introducevideo")!=null) {
				picMap.put("introducevideo", requestMap.get("introducevideo").toString());
			}
			userInfoDao.updateDoctorPic(picMap);
		}
		
		
		if(requestMap.containsKey("diseaseLable") && requestMap.get("diseaseLable") instanceof List) {
			List<String> diseaseLable = new ArrayList<String>();
			diseaseLable = (List<String>) requestMap.get("diseaseLable");
			List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
			for(String str : diseaseLable) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("labelid", str);
				item.put("doctorid", requestMap.get("doctorid").toString());
				item.put("create_date", DateUtil.getSysTime1());
				item.put("del_flag", "0");
				itemList.add(item);
			}
			userInfoDao.deleteDoctorLable(requestMap.get("doctorid").toString());
			userInfoDao.saveDoctorLable(itemList);
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
		result.put("doctorLabel", userInfoDao.findDoctorLabel(id));
		//获取标签
		/*result.put("diseaseLabel", doctorLabelDao.queryList("4"));*/
		
		return result;
	}


	

}
