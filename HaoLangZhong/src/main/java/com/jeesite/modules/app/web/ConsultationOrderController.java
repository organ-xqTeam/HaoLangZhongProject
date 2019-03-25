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
	 * 保存用户咨询订单
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveOrder")
	public Result saveOrder(@RequestBody Map<String, Object> requestMap) {
		try {
			TokenTools.checkToken(requestMap.get("token").toString(), redis);
			Map<String, Object> items = new HashMap<String, Object>();
			items = consultationOrderService.saveConsultationOrder(requestMap);
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
			TokenTools.checkToken(requestParams.getToken(), redis);
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
	
}
