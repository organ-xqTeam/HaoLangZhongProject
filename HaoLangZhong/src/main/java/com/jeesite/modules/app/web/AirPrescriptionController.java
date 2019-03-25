package com.jeesite.modules.app.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.entity.AirPrescription;
import com.jeesite.modules.app.service.AirPrescriptionService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;

/** 
 * @author 范耘诚
 * 药方Controller
 */
@Controller
@RequestMapping(value = "${frontPath}/sys/airPrescriptionController")
public class AirPrescriptionController {
	@Autowired
	private  AirPrescriptionService  airPrescriptionService;
	
	
	/**
	 ** token    token     随便
	 * userId     用户id   123
	 * 展示当前用户的药方列表
	 * /js/f/sys/airPrescriptionController/showAirPrescription
	 * */
	@RequestMapping(value = "/showAirPrescription",method=RequestMethod.POST)
	public Result showAirPrescription(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId= (String) requestParams.get("userId");
			String pageNum= (String) requestParams.get("pageNum");
			String pageSize= (String) requestParams.get("pageSize");
			AirPrescription airPrescription =new AirPrescription();
			airPrescription.setUserId(userId);
		    List airPrescriptionList= airPrescriptionService.findList(airPrescription);
			JSONObject result = new JSONObject();
			result.put("airPrescriptionList", airPrescriptionList);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

}
