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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.DoctorRegisterOrder;
import com.jeesite.modules.app.entity.HospitalInfo;
import com.jeesite.modules.app.entity.HospitalRegister;
import com.jeesite.modules.app.service.DoctorRegisterOrderService;
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
	@Autowired
	private  DoctorRegisterOrderService doctorRegisterOrderService;
	
	
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
				private String doctorUserId  //医生的用户表的userid)
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
	
	
	
	

	
	//查询此挂号订单是否已经付款
	/*
	 * String token=requestParams.get("token").toString();
	   String orderNo=requestParams.get("orderNo").toString();
	   /hospital/getDoctorRegisterOrderSuccess
	 */
	@RequestMapping(value = "/getDoctorRegisterOrderSuccess",method=RequestMethod.POST)
	@ResponseBody
	public Result getDoctorRegisterOrderSuccess(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		
		try {
			String token=requestParams.get("token").toString();
			String orderNo=requestParams.get("orderNo").toString();
			DoctorRegisterOrder doctorRegisterOrder =new DoctorRegisterOrder();
			doctorRegisterOrder.setOrderNo(orderNo);
			List<DoctorRegisterOrder> doctorRegisterOrderList= doctorRegisterOrderService.findList(doctorRegisterOrder);
			if(doctorRegisterOrderList.size()>0) {
				doctorRegisterOrder=doctorRegisterOrderList.get(0);
				String orderStatus= doctorRegisterOrder.getOrderStatus().trim();
				if(orderStatus.equals("1")) {
					/**new CodeMsg(200103,"支付成功");*/
					return  Result.success(CodeMsg.DOCTORORDER_TRUE);
				}else {
					/**	public static CodeMsg DOCTORORDER_FALSE = new CodeMsg(500111,"支队金额有误");	*/
					return Result.success(CodeMsg.DOCTORORDER_FALSE);
				}
			}else {
				System.out.println("订单没找到");
				return Result.success(CodeMsg.PARAMETER_ISNULL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
		   return Result.success(CodeMsg.PARAMETER_ISNULL);
		}
		
		
	}
	/**通过医生的userid得到当前次医生所有的挂号信息
	 * {
	 * 	"token":"123",
	 *  "doctorUserId":"",
	 *  "pageNum":"1",
	 *  "pageSize":"100"
	 * }
	 * /app/hospital/getHospitalRegisterrByDoctorUserId
	 *  */
	@RequestMapping(value = "/getHospitalRegisterrByDoctorUserId",method=RequestMethod.POST)
	@ResponseBody
	public Result getHospitalRegisterrByDoctorUserId(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			String pageNum= requestParams.get("pageNum").toString();
			String pageSize= requestParams.get("pageSize").toString();
			HospitalRegister hospitalRegister=new HospitalRegister();
			hospitalRegister.setDoctorUserId(requestParams.get("doctorUserId").toString());
			hospitalRegister.setPageNo(Integer.valueOf(pageNum));
			hospitalRegister.setPageSize(Integer.valueOf(pageSize));
			List<HospitalRegister> hospitalRegisterList=  hospitalRegisterService.findPage(hospitalRegister).getList();
			hospitalRegister=new HospitalRegister();
			hospitalRegister.setDoctorUserId(requestParams.get("doctorUserId").toString());
			String count= ( (Long)hospitalRegisterService.findCount(hospitalRegister)).toString();
			JSONObject result = new JSONObject();
			result.put("hospitalRegisterList", hospitalRegisterList);
			result.put("count", count);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
}
