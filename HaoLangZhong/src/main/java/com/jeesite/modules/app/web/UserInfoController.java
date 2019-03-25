package com.jeesite.modules.app.web;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.service.UserInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.TokenTools;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
 * 用户信息controller
 * */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/userInfo")
public class UserInfoController extends BaseController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private StringRedisTemplate redis;
	
	/**
	 * 保存用户信息
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveUserInfo")
	public Result saveUserInfo(@RequestBody Map<String, Object> requestMap) {
		try {
			TokenTools.checkToken(requestMap.get("token").toString(), redis);
			return Result.success(userInfoService.saveUserInfo(requestMap));			
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
	 * 医生提交验证
	 * */
	@ResponseBody
	@RequestMapping(value = "/validate")
	public Result validate(@RequestBody Map<String, Object> requestMap) {
		try {
			TokenTools.checkToken(requestMap.get("token").toString(), redis);
			userInfoService.saveValidate(requestMap);
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
	 * 医生编辑个人信息
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveDoctorInfo")
	public Result saveDoctorInfo(@RequestBody Map<String, Object> requestMap) {
		try {
			TokenTools.checkToken(requestMap.get("token").toString(), redis);
			userInfoService.saveDoctorInfo(requestMap);
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
