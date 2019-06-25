package com.jeesite.modules.app.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.ConsultationDiscuss;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.service.ConsultationDiscussService;
import com.jeesite.modules.app.service.ConsultationService;
import com.jeesite.modules.app.service.DoctorLabelService;
import com.jeesite.modules.app.service.UserInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
 * 咨询信息管理Controller
 * @author 李昊翀
 * @version 2019-03-14
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/consultation")
public class ConsultationController extends BaseController {

	@Autowired
	private DoctorLabelService doctorLabelService;
	
	@Autowired
	private ConsultationService consultationService;
	@Autowired
	private  ConsultationDiscussService  consultationDiscussService;
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 获取咨询信息详情
	 * */
	@ResponseBody
	@RequestMapping(value = "/findConsultationById/{id}")
	public Result findConsultationById(@PathVariable String id) {
		try {
			JSONObject resultJson = new JSONObject();
			Map<String, Object> consultation = consultationService.findConsultationById(id);
			/*String body = consultation.get("body").toString();
			String therapy = consultation.get("therapy").toString();*/
			String disease=null;
			if(consultation.get("disease")!=null) {
				disease = consultation.get("disease").toString();
				
			}
			/*String [] bodys = body.split(",");
			String [] therapys = therapy.split(",");*/
			List<String> therapyArr = new ArrayList<String>();
			List<String> bodyArr = new ArrayList<String>();
			List<String> diseaseArr=new ArrayList<String>();
			if(disease!=null) {
				diseaseArr.add(disease);
			}
			/*for(String s : bodys) {
				bodyArr.add(s);
			}
			for(String s : therapys) {
				therapyArr.add(s);
			}*/
			resultJson.put("consultation", consultation);
			resultJson.put("pics", consultationService.findConsultationPic(consultation.get("id").toString()));
			/*resultJson.put("body", doctorLabelService.queryListByIds(bodyArr));
			resultJson.put("therapys", doctorLabelService.queryListByIds(therapyArr));*/
			if(diseaseArr.size()>0) {
				resultJson.put("disease", doctorLabelService.queryListByIds(diseaseArr));
			}
			ConsultationDiscuss consultationDiscuss =new ConsultationDiscuss();
			consultationDiscuss.setConsultationId(id);
			List<ConsultationDiscuss> consultationDiscussList= consultationDiscussService.findList(consultationDiscuss);
			
			resultJson.put("consultationDiscussList", consultationDiscussList);
			
			Object userIdObj=  consultation.get("user_id");
			String userId=null;
			if(userIdObj!=null) {
				userId=(String) userIdObj;
			}
			UserInfo userInfo  =new UserInfo();
			userInfo=userInfoService.get(userId);
			resultJson.put("userInfo", userInfo);
			return Result.success(resultJson);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/**
	 * "token":token,
						 "userId":userId,
						 "inputval",inputval
	 */
	/**
	 * 发送讨论信息的操作
	 */
	@ResponseBody
	@RequestMapping(value = "/sendInfo")
	public Result sendInfo(@RequestBody Map<String, Object> requestMap) {
		try {
			//TokenTools.checkToken(requestMap.get("token").toString(), redis);
			requestMap.get("token");
			String userId=(String) requestMap.get("userId");
			String inputval=(String) requestMap.get("inputval");
			String orderId=(String) requestMap.get("orderId");
			String discussState=null;
			if(requestMap.get("discussState")!=null) {discussState=(String) requestMap.get("discussState");}
			/*consultationOrderService.updateOrderPay(requestMap);*/
			ConsultationDiscuss consultationDiscuss =new ConsultationDiscuss();
			consultationDiscuss.setUserId(userId);
			consultationDiscuss.setContent(inputval);
			consultationDiscuss.setConsultationId(orderId);
			consultationDiscuss.setCreateDate(new Date());
			if(discussState!=null) {
				consultationDiscuss.setDiscussState(discussState);
			}
			consultationDiscussService.insert(consultationDiscuss);
			return Result.success(true);			
		}
		catch (RedisCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.TOKEN_INVALID);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
}
