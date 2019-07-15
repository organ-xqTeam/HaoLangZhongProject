/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.DashangOrder;
import com.jeesite.modules.app.entity.DoctorInfos;
import com.jeesite.modules.app.entity.DoctorRegisterOrder;
import com.jeesite.modules.app.entity.HospitalRegister;
import com.jeesite.modules.app.entity.MemberOrder;
import com.jeesite.modules.app.entity.Order;
import com.jeesite.modules.app.entity.TiaoliOrder;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.service.DashangOrderService;
import com.jeesite.modules.app.service.HospitalRegisterService;
import com.jeesite.modules.app.service.MemberOrderService;
import com.jeesite.modules.app.service.OrderService;
import com.jeesite.modules.app.service.TiaoliOrderService;
import com.jeesite.modules.app.service.UserInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;

/**
 * sys_orderController
 * @author 范耘诚
 * @version 2019-03-15
 */
// /js/f/app/order/getTiaoliOrderList
@Controller
@RequestMapping(value = "${frontPath}/app/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TiaoliOrderService tiaoliOrderService;
	@Autowired
	private UserInfoService userInfoService;
	
	
	/*
	 * /order/getTiaoliOrderList
	 */
	//查找此用户下的调理订单列表
	@ResponseBody
	@RequestMapping(value = "/getTiaoliOrderList", method = RequestMethod.POST)
	public Result getTiaoliOrderList(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token= requestParams.get("token").toString();
			String userId= requestParams.get("userId").toString();
			String pageNum= requestParams.get("pageNum").toString();
			String pageSize= requestParams.get("pageSize").toString();
			TiaoliOrder tiaoliOrder =new TiaoliOrder();
			tiaoliOrder.setUserid(userId);
			tiaoliOrder.setOrderStatus("1");
			tiaoliOrder.setPageNo(Integer.valueOf(pageNum));
			tiaoliOrder.setPageSize(Integer.valueOf(pageSize));
			List<TiaoliOrder> tiaoliOrderList= tiaoliOrderService.findPage(tiaoliOrder).getList();
			for (TiaoliOrder tiaoliOrder2 : tiaoliOrderList) {
				
				DoctorInfos  doctorInfos=tiaoliOrder2.getDoctorInfos();
				String docid= doctorInfos.getDoctorid();
				UserInfo userinfo =new UserInfo();
				userinfo.setId(docid);
				userinfo=userInfoService.get(userinfo);
				doctorInfos.setUserInfo(userinfo);
			}
			tiaoliOrder =new TiaoliOrder();
			tiaoliOrder.setUserid(userId);
			tiaoliOrder.setOrderStatus("1");
			String count= ( (Long)tiaoliOrderService.findCount(tiaoliOrder)).toString();
			JSONObject result = new JSONObject();
			result.put("items", tiaoliOrderList);
			result.put("count", count);
			/*
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryConsultationList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCommentCount(requestParams));
			*/
			
			return Result.success(result);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	@Autowired
	private HospitalRegisterService hospitalRegisterService ;
	//查找此用户下的挂号订单
	/**
	 * /order/getGuaHaoOrderList
	 * */
	@ResponseBody
	@RequestMapping(value = "/getGuaHaoOrderList", method = RequestMethod.POST)
	public Result getGuaHaoOrderList(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			String token= requestParams.get("token").toString();
			String userId= requestParams.get("userId").toString();
			String pageNum= requestParams.get("pageNum").toString();
			String pageSize= requestParams.get("pageSize").toString();
			HospitalRegister hospitalRegister =new HospitalRegister();
			hospitalRegister.setUserId(userId);
			hospitalRegister.setPageNo(Integer.valueOf(pageNum));
			hospitalRegister.setPageSize(Integer.valueOf(pageSize));
			List<HospitalRegister>  hospitalRegisterList= hospitalRegisterService.findPage(hospitalRegister).getList();
			for (HospitalRegister hospitalRegisters : hospitalRegisterList) {
				
				hospitalRegisters.getDoctorUserId();
			}
			
			
			hospitalRegister =new HospitalRegister();
			hospitalRegister.setUserId(userId);
			String count= ( (Long)hospitalRegisterService.findCount(hospitalRegister)).toString();
			JSONObject result = new JSONObject();
			result.put("items", hospitalRegisterList);
			result.put("count", count);
			/*PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryConsultationList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCommentCount(requestParams));*/
			
			return Result.success(result);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	
	
	//查询此调理订单是否支付成功
	/*
	 * String token=requestParams.get("token").toString();
	   String orderNo=requestParams.get("orderNo").toString();
	   /order/getTiaoLiOrderSuccess
	 */
	@RequestMapping(value = "/getTiaoLiOrderSuccess",method=RequestMethod.POST)
	@ResponseBody
	public Result getTiaoLiOrderSuccess(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			String token=requestParams.get("token").toString();
			String orderNo=requestParams.get("orderNo").toString();
			TiaoliOrder tiaoLiOrder =new TiaoliOrder();
			tiaoLiOrder.setOrderNo(orderNo);
			List<TiaoliOrder> tiaoliOrderList= tiaoliOrderService.findList(tiaoLiOrder);
			if(tiaoliOrderList.size()>0) {
				tiaoLiOrder=tiaoliOrderList.get(0);
				String orderStatus= tiaoLiOrder.getOrderStatus().trim();
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
	@Autowired
	private MemberOrderService memberOrderService;
	//查询此会员订单是否成功
	/*
	 * String token=requestParams.get("token").toString();
	   String orderNo=requestParams.get("orderNo").toString();
	   /order/getMemberOrderSuccess
	 */
	@RequestMapping(value = "/getMemberOrderSuccess",method=RequestMethod.POST)
	@ResponseBody
	public Result getMemberOrderSuccess(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			String token=requestParams.get("token").toString();
			String orderNo=requestParams.get("orderNo").toString();
			MemberOrder memberOrder =new MemberOrder();
			memberOrder.setOrderNo(orderNo);
			List<MemberOrder> memberOrderList= memberOrderService.findList(memberOrder);
			if(memberOrderList.size()>0) {
				memberOrder=memberOrderList.get(0);
				String orderStatus= memberOrder.getOrderStatus().trim();
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
	//查询此会员订单是否成功
	/*
	 * String token=requestParams.get("token").toString();
	   String orderNo=requestParams.get("orderNo").toString();
	   /order/getOrderSuccess
	 */
	@RequestMapping(value = "/getOrderSuccess",method=RequestMethod.POST)
	@ResponseBody
	public Result getOrderSuccess(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			String token=requestParams.get("token").toString();
			String orderNo=requestParams.get("orderNo").toString();
			Order order =new Order();
			order.setOrderNo(orderNo);
			List<Order> orderList= orderService.findList(order);
			if(orderList.size()>0) {
				order=orderList.get(0);
				String orderStatus= order.getOrderStatus().trim();
				if(Integer.valueOf(orderStatus)>=1) {
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
	@Autowired
	private  DashangOrderService dashangOrderService;
	//查询此打赏订单是否成功
	/*
	 * String token=requestParams.get("token").toString();
	   String orderNo=requestParams.get("orderNo").toString();
	   app/order/getDashangOrderSuccess
	 */
	@RequestMapping(value = "/getDashangOrderSuccess",method=RequestMethod.POST)
	@ResponseBody
	public Result getDashangOrderSuccess(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			String token=requestParams.get("token").toString();
			String orderNo=requestParams.get("orderNo").toString();
			DashangOrder dashangOrder =new DashangOrder();
			dashangOrder.setOrderNo(orderNo);
			List<DashangOrder> dashangOrderList= dashangOrderService.findList(dashangOrder);
			if(dashangOrderList.size()>0) {
				dashangOrder=dashangOrderList.get(0);
				String orderStatus= dashangOrder.getOrderStatus().trim();
				if(Integer.valueOf(orderStatus)>=1) {
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
	
	
	
	
	
	
	
	
	
	/**
	 * 获取数据
	 */
/*	@ModelAttribute
	public Order get(Long id, boolean isNewRecord) {
		return orderService.get(id.toString(), isNewRecord);
	}
	
	*//**
	 * 查询列表
	 *//*
	@RequiresPermissions("app:order:view")
	@RequestMapping(value = {"list", ""})
	public String list(Order order, Model model) {
		model.addAttribute("order", order);
		return "modules/app/orderList";
	}
	
	*//**
	 * 查询列表数据
	 *//*
	@RequiresPermissions("app:order:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Order> listData(Order order, HttpServletRequest request, HttpServletResponse response) {
		order.setPage(new Page<>(request, response));
		Page<Order> page = orderService.findPage(order);
		return page;
	}

	*//**
	 * 查看编辑表单
	 *//*
	@RequiresPermissions("app:order:view")
	@RequestMapping(value = "form")
	public String form(Order order, Model model) {
		model.addAttribute("order", order);
		return "modules/app/orderForm";
	}

	*//**
	 * 保存sys_order
	 *//*
	@RequiresPermissions("app:order:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Order order) {
		orderService.save(order);
		return renderResult(Global.TRUE, text("保存sys_order成功！"));
	}
	
	*//**
	 * 删除sys_order
	 *//*
	@RequiresPermissions("app:order:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Order order) {
		orderService.delete(order);
		return renderResult(Global.TRUE, text("删除sys_order成功！"));
	}*/
	
}