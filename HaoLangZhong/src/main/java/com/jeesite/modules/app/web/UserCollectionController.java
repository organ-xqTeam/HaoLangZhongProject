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
import com.jeesite.modules.app.entity.UserCollection;
import com.jeesite.modules.app.service.UserCollectionService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.DateUtil;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.TokenTools;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
 * 用户收藏controller
 * */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/userCollection")
public class UserCollectionController extends BaseController {

	@Autowired
	private UserCollectionService userCollectionService;
	
	@Autowired
	private StringRedisTemplate redis;
	
	/**
	 * 获取用户收藏的医生列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryDoctorList")
	public Result queryDoctorList(@RequestBody UserCollection requestParams) {
		try {
			/*TokenTools.checkToken(requestParams.getToken(), redis);*/
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = userCollectionService.queryDoctorList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", userCollectionService.queryDoctorCount(requestParams));
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
	 * 获取用户收藏的医生列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryArticleList")
	public Result queryArticleList(@RequestBody UserCollection requestParams) {
		try {
			/*TokenTools.checkToken(requestParams.getToken(), redis);*/
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = userCollectionService.queryArticleList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", userCollectionService.queryArticleCount(requestParams));
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
	 * 保存用户收藏信息
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveUserCollection")
	public Result saveUserCollection(@RequestBody Map<String, Object> requestParams) {
		try {
			/*TokenTools.checkToken(requestParams.get("token").toString(), redis);*/
			requestParams.put("create_date", DateUtil.getSysTime1());
			requestParams.put("del_flag", "0");
			userCollectionService.saveUserCollection(requestParams);
			return Result.success(requestParams);
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
	 * 取消收藏
	 * */
	@ResponseBody
	@RequestMapping(value = "/cancelCollection/{userid}/{collectionid}/{token}")
	public Result cancelCollection(@PathVariable String userid, @PathVariable String collectionid, @PathVariable String token) {
		try {
			/*TokenTools.checkToken(token, redis);*/
			userCollectionService.cancelCollection(userid, collectionid);
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
