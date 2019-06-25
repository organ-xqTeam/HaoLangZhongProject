package com.jeesite.modules.app.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.HospitalInfo;
import com.jeesite.modules.app.entity.HospitalRegister;
import com.jeesite.modules.app.service.HospitalInfoService;
import com.jeesite.modules.app.service.HospitalRegisterService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;

@Controller
@RequestMapping(value = "${frontPath}/app/hospital")
public class HospitalController extends BaseController {
	@Autowired
	private HospitalInfoService hospitalInfoService;
	@Autowired
	private HospitalRegisterService hospitalRegisterService;
	
	
	/**
	 ** token    token     随便
	 * 
	 *     展示好郎中医院的医院简介
	 * /js/f/app/hospital/showHospitalInfoMain
	 * */
	@RequestMapping(value = "/showHospitalInfoMain",method=RequestMethod.POST)
	public Result showHospitalInfoMain(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			
			HospitalInfo hospitalInfo =new HospitalInfo();
			List<HospitalInfo> hospitalInfoList= hospitalInfoService.findList(hospitalInfo);
			JSONObject result = new JSONObject();
			if(hospitalInfoList.size()>0) {
				result.put("hospitalInfo", hospitalInfoList.get(0));
			}
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/**
	 ** token    token     随便
	 * 
	 *      提交网上挂号
	 * /js/f/app/hospital/submitHospitalRegister
	 * */
	@RequestMapping(value = "/submitHospitalRegister",method=RequestMethod.POST)
	public Result submitHospitalRegister(HttpServletRequest request,@RequestBody HospitalRegister hospitalRegister) {
		try {
			//HospitalRegister hospitalRegister =new HospitalRegister();
			/*
			 * 	private String name;		// 姓名
				private String phone;		// 电话
				private String gender;		// 性别
				private String genderType;		// 0不明 1男 2女
				private String age;		// 年龄
				private Date makeDate;		// 预约时间
				private String content;		// 症状描述
				private String userId;		// user_id
			 */
			//System.out.println(requestParams);
			//System.out.println(makeDates);
			
			
			//创建时间
			hospitalRegister.setCreateDate(new Date());
			Date makeDate= new Date();
			if(hospitalRegister.getMakeDates()!=null) {
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				makeDate=sdf.parse(hospitalRegister.getMakeDates());
			}
			//预约时间
			hospitalRegister.setMakeDate(makeDate);
			
			System.out.println(hospitalRegister);
			hospitalRegisterService.save(hospitalRegister);
			//result.put("hospitalInfo", hospitalInfoList.get(0));
			return Result.success(CodeMsg.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.success(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	
	/**
	 ** token    token     随便
	 * 
	 *  通过id查找此用户所有挂号信息
	 * /js/f/app/hospital/getHospitalRegisterByUserId
	 * {
	 * 	"token":"123",
	 *  "userId":""
	 * }
	 * */
	@RequestMapping(value = "/getHospitalRegisterByUserId",method=RequestMethod.POST)
	public Result getHospitalRegisterByUserId(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			HospitalRegister hospitalRegister=new HospitalRegister();
			hospitalRegister.setUserId(requestParams.get("userId").toString());
			List<HospitalRegister> hospitalRegisterList=  hospitalRegisterService.findList(hospitalRegister);
			JSONObject result = new JSONObject();
			result.put("hospitalRegisterList", hospitalRegisterList);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
}
