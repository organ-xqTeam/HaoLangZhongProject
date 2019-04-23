package com.jeesite.modules.app.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.Consultation;
import com.jeesite.modules.app.service.ConsultationOrderService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.TokenTools;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
 * 咨询订单管理Controller
 * @author 李昊翀
 * @version 2019-02-28
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/consultationOrder")
public class ConsultationOrderController extends BaseController {
	
	@Autowired
	private ConsultationOrderService consultationOrderService;
	
	@Autowired
	private StringRedisTemplate redis;
	
	/**
	 * "hlz/sys/consultationOrder/saveOrder
	 * 保存用户咨询订单
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveOrder")
	public Result saveOrder(@RequestBody Map<String, Object> requestMap) {
		try {
			/*TokenTools.checkToken(requestMap.get("token").toString(), redis);*/
			Map<String, Object> items = new HashMap<String, Object>();
			items = consultationOrderService.saveConsultationOrder(requestMap);
			Map<String, Object> requestModel= new HashMap<>();
			
			
			//默认已经支付并接单
			/*{
				"id":"539d31f73b2711e9a5e3bcaec5595fa3",		//*必填 订单id
				"orderstate":"3",								//*必填 订单状态
				"paytype":"1"									//*必填 支付方式
			"token":"9fe32383047040abaa9b855c39d99c9b"		//*必填 用户token
			}*/
			/*System.out.println(items.get("id"));
			requestModel.put("id", items.get("id"));
			requestModel.put("orderstate", "2");
			//支付成功已接单
			requestModel.put("paytype", "4");
			consultationOrderService.updateConsultationOrder(requestModel);*/
			return Result.success(items);
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
	
	/**
	 * 修改用户咨询订单
	 * */
	@ResponseBody
	@RequestMapping(value = "/updateOrder")
	public Result updateOrder(@RequestBody Map<String, Object> requestMap) {	
		try {
			TokenTools.checkToken(requestMap.get("token").toString(), redis);
			consultationOrderService.updateConsultationOrder(requestMap);
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
	
	/**
	 * 查询用户的问诊列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryList")
	public Result queryList(@RequestBody Consultation requestParams) {
		try {
			/*TokenTools.checkToken(requestParams.getToken(), redis);*/
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = consultationOrderService.queryList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", consultationOrderService.queryCount(requestParams));
			return Result.success(result);			
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
	
	/**
	 * 医生回复问诊订单
	 * */
	@ResponseBody
	@RequestMapping(value = "/reply")
	public Result reply(@RequestBody Map<String, Object> requestMap) {
		try {
			TokenTools.checkToken(requestMap.get("token").toString(), redis);
			consultationOrderService.reply(requestMap);
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
	
	/**
	 * 通过主键查找咨询订单的操作
	 */
	@ResponseBody
	@RequestMapping(value = "/getConsultationOrderByOrderId")
	public Result getConsultationOrderByOrderId(@RequestBody Map<String, Object> requestMap) {
		try {
			//TokenTools.checkToken(requestMap.get("token").toString(), redis);
			/**通过主键查找咨询订单*/
			Map<String, Object> consultationOrderMap=  consultationOrderService.getConsultationOrderByOrderId(requestMap);
			Map<String, Object> items = new HashMap<String, Object>();
			items = consultationOrderMap;
			return Result.success(items);			
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
	/**
	 * 支付订单的操作
	 */
	@ResponseBody
	@RequestMapping(value = "/payOrder")
	public Result payOrder(@RequestBody Map<String, Object> requestMap) {
		try {
			//TokenTools.checkToken(requestMap.get("token").toString(), redis);
			/**更新成已支付状态*/
			consultationOrderService.updateOrderPay(requestMap);
			
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
