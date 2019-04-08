package com.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.User;
import com.jeesite.modules.app.service.UserLoginService;
import com.jeesite.modules.app.service.UserShareService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.exception.CodeCheckException;
import com.jeesite.modules.app.utils.exception.MobileRepeatException;
import com.jeesite.modules.app.utils.exception.NotExistException;
import com.jeesite.modules.app.utils.exception.NotLoginException;
import com.jeesite.modules.app.utils.exception.PassCheckException;
import com.jeesite.modules.app.utils.exception.WrongPassException;

/**
 * 用户登录注册
 * */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/user")
public class UserLoginController extends BaseController {

	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private UserShareService userShareService;
	
	/**
	 * 用户登录
	 * */
	@ResponseBody
	@RequestMapping(value = "/login")
	public Result login(@RequestBody User user) throws Exception {
		try {
			JSONObject result = userLoginService.login(user);
			Object isauth= result.get("isauth");
			
			if(isauth!=null) {
				Boolean isauthFlag=(Boolean) isauth;
				if(isauthFlag==false) {
					//需跳转审核页面
					return Result.error(CodeMsg.ISAUTH_FALSE);
				}else {
					//审核中
					return Result.error(CodeMsg.ISAUTH_TRUE);
				}
			}
			return Result.success(result);
		}
		catch (NotExistException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.NOT_EXIST);
		}
		catch (WrongPassException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.WRONG_PASS);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 退出登录
	 * */
	@ResponseBody
	@RequestMapping(value = "/cancel/{token}")
	public Result cancel(@PathVariable String token) throws Exception {
		try {
			Object result = userLoginService.cancel(token);
			return Result.success(result);
		}
		catch (NotLoginException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.NOT_LOGIN);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 用户注册
	 * f/sys/user/register
	 * */
	@ResponseBody
	@RequestMapping(value = "/register")
	public Result register(@RequestBody User user) throws Exception {
		try {
			JSONObject result = userLoginService.register(user);
			//userShareService.UserRegisterShareGetDiscount(user);
			return Result.success(result);
		}
		catch (PassCheckException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.PASS_CHECK);
		}
		catch (CodeCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.CODE_CHECK);
		}
		catch (MobileRepeatException e3) {
			logger.error(e3.getMessage(), e3);
			return Result.error(CodeMsg.MOBILE_REPEAT);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 修改密码
	 * */
	@ResponseBody
	@RequestMapping(value = "/changePassword")
	public Result changePassword(@RequestBody User user) throws Exception {
		try {
			Object result = userLoginService.changePassword(user);
			return Result.success(result);
		}
		catch (NotExistException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.NOT_EXIST);
		}
		catch (WrongPassException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.WRONG_PASS);
		}
		catch (PassCheckException e3) {
			logger.error(e3.getMessage(), e3);
			return Result.error(CodeMsg.PASS_CHECK);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 找回密码
	 * */
	@ResponseBody
	@RequestMapping(value = "/forgetPassword")
	public Result forgetPassword(@RequestBody User user) throws Exception {
		try {
			Object result = userLoginService.forgetPassword(user);
			return Result.success(result);
		}
		catch (NotExistException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.NOT_EXIST);
		}
		catch (PassCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.PASS_CHECK);
		}
		catch (CodeCheckException e3) {
			logger.error(e3.getMessage(), e3);
			return Result.error(CodeMsg.CODE_CHECK);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
}
