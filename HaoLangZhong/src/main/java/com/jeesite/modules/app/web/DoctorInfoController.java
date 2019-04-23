/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.web;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.dao.CutDao;
import com.jeesite.modules.app.entity.Cut;
import com.jeesite.modules.app.entity.DoctorInfo;
import com.jeesite.modules.app.service.DoctorInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.TokenTools;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
 * 医生管理Controller
 * @author 李昊翀
 * @version 2019-02-21
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/doctorInfo")
public class DoctorInfoController extends BaseController {

	@Autowired
	private DoctorInfoService doctorInfoService;
	
	@Autowired
	private StringRedisTemplate redis;
	
	/**
	 * 用户端根据医生标签检索医生列表
	 * /doctorInfo/queryListByLabel
	 *就用这个
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryListByLabel")
	public Result queryListByLabel(@RequestBody DoctorInfo requestParams) {
		try {
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setCount(requestParams.getLabels().size());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryListByLabel(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCountByLabel(requestParams));
			return Result.success(result);			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 用户端多条件查询医生列表
	 * doctorInfo/queryList
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryList")
	public Result queryList(@RequestBody DoctorInfo requestParams) {
		try {
			System.out.println(requestParams);
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCount(requestParams));
			return Result.success(result);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/**
	 * 用户端多条件查询本院医生
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryListOwn")
	public Result queryListOwn(@RequestBody DoctorInfo requestParams) {
		try {
			System.out.println(requestParams);
			requestParams.setOwnFlag("1");
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryOwedList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryOwedCount(requestParams));
			return Result.success(result);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 查询医生的详细信息
	 * */
	@ResponseBody
	@RequestMapping(value = "/findOneByDoctorid/{doctorid}/{token}")
	public Result findOneByDoctorid(@PathVariable String doctorid, @PathVariable String token) {
		try {			
			/*TokenTools.checkToken(token, redis);*/
			return Result.success(doctorInfoService.findOneByDoctorid(doctorid));
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
	 * 查询医生评论列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryCommentList")
	public Result queryCommentList(@RequestBody DoctorInfo requestParams) {
		try {
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryCommentList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCommentCount(requestParams));
			return Result.success(result);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 查询医生问诊列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryConsultationList")
	public Result queryConsultationList(@RequestBody DoctorInfo requestParams) {
		try {
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryConsultationList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCommentCount(requestParams));
			return Result.success(result);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 查询医生订单数量
	 * */
	@ResponseBody
	@RequestMapping(value = "/findOrderCount/{id}/{token}")
	public Result findOrderCount(@PathVariable String id, @PathVariable String token) {
		try {			
			TokenTools.checkToken(token, redis);
			return Result.success(doctorInfoService.findOrderCount(id));
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
	 * 查询医生钱包明细列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryIncomeList")
	public Result queryIncomeList(@RequestBody DoctorInfo requestParams) {
		try {
			/*TokenTools.checkToken(requestParams.getToken(), redis);*/
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryIncomeList(requestParams);
			Cut cut =new Cut();
			List<Cut> cutList= cutDao.findList(cut);
			double doucut=0;
			if(cutList!=null&&cutList.size()>0) {
				String cutStr= cutList.get(0).getCut();
				if(cutStr!=null) {
					doucut= new Double(cutStr) ;
				}	
			}
			for (int i = 0; i < resultList.size(); i++) {
				Double amount= new Double(resultList.get(i).get("amount").toString()) ;
				amount=doucut*amount;
				resultList.get(i).put("amount", amount);
			}
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryIncomeCount(requestParams));
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
	@Autowired
	private CutDao cutDao;
	
	/**
	 * 查询医生钱包余额
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryIncomeSum")
	public Result queryIncomeSum(@RequestBody DoctorInfo requestParams) {
		try {			
			/*TokenTools.checkToken(requestParams.getToken(), redis);*/
			Double incomeSum = doctorInfoService.queryIncomeSum(requestParams);
			Cut cut =new Cut();
			List<Cut> cutList= cutDao.findList(cut);
			if(cutList!=null&&cutList.size()>0) {
				String cutObj= cutList.get(0).getCut();
				if(cutObj!=null) {
					double doucut= new Double(cutObj) ;
					incomeSum=incomeSum*doucut;
				}
			}
			return Result.success(incomeSum);
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