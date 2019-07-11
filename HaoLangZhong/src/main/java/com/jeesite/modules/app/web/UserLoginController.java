package com.jeesite.modules.app.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.User;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.service.UserInfoService;
import com.jeesite.modules.app.service.UserLoginService;
import com.jeesite.modules.app.service.UserShareService;
import com.jeesite.modules.app.utils.AliMessageUtils;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.DateUtil;
import com.jeesite.modules.app.utils.MD5Utils;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.TokenProccessor;
import com.jeesite.modules.app.utils.exception.CodeCheckException;
import com.jeesite.modules.app.utils.exception.MobileRepeatException;
import com.jeesite.modules.app.utils.exception.NotExistException;
import com.jeesite.modules.app.utils.exception.NotLoginException;
import com.jeesite.modules.app.utils.exception.PassCheckException;
import com.jeesite.modules.app.utils.exception.WrongPassException;

/**
 * 用户登录注册
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/user")
public class UserLoginController extends BaseController {

	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private UserShareService userShareService;
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private StringRedisTemplate redis;
	@Autowired 
	private RedisTemplate redisTemplate;
	/**
	 * 第三方登录 token
	 *  唯一标准标识 third_id 
	 * 第三方类型 third_type 0 无第三方 1微信 2qq 
	 * user_type 第三方登录类型 1.普通用户，2.医生
	 *  /sys/user/thirdLogin
	 */
	/**
	 * 第三方登录
	 */
	@ResponseBody
	@RequestMapping(value = "/thirdLogin")
	public Result thirdLogin(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {

		try {
			String userType = requestParams.get("user_type").toString();
			String thirdType = requestParams.get("third_type").toString();
			String thirdId = requestParams.get("third_id").toString();
			UserInfo userInfo = new UserInfo();
			//设置第三方的id
			userInfo.setThirdId(thirdId);
			//设置第三方的类型
			userInfo.setThirdType(thirdType);
			// 通过唯一标识查找
			List<UserInfo> userInfoList = userInfoService.findList(userInfo);
			if (userInfoList.size() > 0) {
				// 有此用户信息走登录接口
				userInfo = userInfoList.get(0);
				User user = new User();
				user.setMobile(userInfo.getMobile());
				user.setType(userInfo.getType());
				JSONObject result = userLoginService.thirdLogin(user);
				Object isauth = result.get("isauth");
				if (isauth != null) {
					Boolean isauthFlag = (Boolean) isauth;
					if (isauthFlag == false) {
						// 需跳转审核页面
						return Result.error(CodeMsg.ISAUTH_FALSE);
					} else {
						// 审核中
						return Result.error(CodeMsg.ISAUTH_TRUE);
					}
				}
				return Result.success(result);
			} else {
				// 如果没有用户信息的话前端跳转登录接口
				return Result.error(CodeMsg.JUMP_BIND);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/**
	 * 第三方与userInfo表绑定
	 * 	 * 唯一标准 third_id 
	 * 第三方类型 third_type 0 无第三方 1微信 2qq
	 * user_type 第三方登录类型 1.普通用户，2.医生 
	 * 手机号 mobile 
	 * 验证码 code
	 * f/sys/user/bindThirdUserInfo
	 */
	@ResponseBody
	@RequestMapping(value = "/bindThirdUserInfo")
	public Result BindThirdUserInfo(HttpServletRequest request, @RequestBody Map<String, Object> requestParams) {
		try {
			
			String userType = requestParams.get("user_type").toString();
			String thirdType = requestParams.get("third_type").toString();
			String thirdId = requestParams.get("third_id").toString();
			String mobile = requestParams.get("mobile").toString();
			String code = requestParams.get("code").toString();
			//查看手机号是否被注册
			UserInfo userInfo =new UserInfo();
			userInfo.setMobile(mobile);
			/** 第三方与userInfo表绑定*/
			List<UserInfo> UserInfoList= userInfoService.findList(userInfo);
			if(UserInfoList.size()>0) {
				//不为空的话输入验证码验证进行绑定
				//code验证
				String serverCode= redis.opsForValue().get(mobile);
				if(serverCode==null||serverCode.equals("")) {
					//验证码不正确	public static CodeMsg CODE_EXCEPTION = new CodeMsg(500111,"验证码不正确");
					return Result.error(CodeMsg.CODE_EXCEPTION);
				}
				if(!code.equals(serverCode)) {
					//验证码不正确 	public static CodeMsg CODE_EXCEPTION = new CodeMsg(500111,"验证码不正确");
					return Result.error(CodeMsg.CODE_EXCEPTION);
				}
				/*
				 * 		@Column(name="third_id", attrName="thirdId", label="第三方的id"), 
						@Column(name="third_type", attrName="thirdType", label="第三方类型 0无第三方 1:微信 2:qq"), 
						@Column(name="third_icon", attrName="thirdIcon", label="第三方头像上传的id"), 
				 */
				//验证成功后进行插入操作
				
				userInfo=UserInfoList.get(0);
				//放入第三方的id和类型
				userInfo.setThirdId(thirdId);
				userInfo.setThirdType(thirdType);
				userInfo.setUpdateDate(new Date());
				//更新userinfo
				userInfoService.update(userInfo);
				
				return Result.success(CodeMsg.TRUE_THIRD_BING);
			}else {
				//为空证明mobile不存在
				//验证code
				String serverCode= redis.opsForValue().get(mobile);
				if(serverCode==null||serverCode.equals("")) {
					//验证码不正确	public static CodeMsg CODE_EXCEPTION = new CodeMsg(500111,"验证码不正确");
					return Result.error(CodeMsg.CODE_EXCEPTION);
				}
				if(!code.equals(serverCode)) {
					//验证码不正确	public static CodeMsg CODE_EXCEPTION = new CodeMsg(500111,"验证码不正确");
					return Result.error(CodeMsg.CODE_EXCEPTION);
				}else {
					//如果验证码正确的话让其走注册接口  true
					thirdRegister(requestParams);
					return Result.success(CodeMsg.TRUE_THIRD_RE);
				}
				

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
		
	}
	
	/**
	 * 跳转到第三方与与手机号绑定的接口 token 
	 * 唯一标准 third_id 
	 * 第三方类型 third_type 0 无第三方 1微信 2qq
	 * user_type 第三方登录类型 1.普通用户，2.医生 手机号 mobile 
	 * f/sys/user/thirdRegister
	 */
	/*@ResponseBody
	@RequestMapping(value = "/thirdRegister")*/
	public Result thirdRegister(@RequestBody Map<String, Object> requestParams) {
		try {
			
			String userType = requestParams.get("user_type").toString();
			String thirdType = requestParams.get("third_type").toString();
			String thirdId = requestParams.get("third_id").toString();
			String mobile = requestParams.get("mobile").toString();
			/*String code = requestParams.get("code").toString();*/
			User user = new User();
			user.setMobile(mobile);
			user.setType(userType);
			//验证验证码
			/*user.setCode(code);*/
			JSONObject result = userLoginService.thirdRegister(user,requestParams);
			
			return Result.success(result);
		} catch (PassCheckException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.PASS_CHECK);
		} catch (CodeCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.CODE_CHECK);
		} catch (MobileRepeatException e3) {
			logger.error(e3.getMessage(), e3);
			return Result.error(CodeMsg.MOBILE_REPEAT);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}

	}
	/***用户登录获取验证码接口
	 * f/sys/user/getCode
	 * 手机号 mobile
	 * */
	@ResponseBody
	@RequestMapping(value = "/getCode")
	public Result getCode(@RequestBody Map<String, Object> requestParams) throws Exception {
		
		try {
			String mobile= requestParams.get("mobile").toString();
			boolean mobileFlag= redis.hasKey(mobile);
			if(mobileFlag) {
				redis.delete(mobile);
			}
			String code=AliMessageUtils.sendMsg(mobile);
			//3分钟有效时间
			redis.opsForValue().set(mobile, code,180,TimeUnit.SECONDS);
			/*JSONObject result = new JSONObject();*/
			return Result.success(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.success(CodeMsg.PARAMETER_ISNULL);
		}
	}
	




	/**
	 * 用户登录
	 */
	@ResponseBody
	@RequestMapping(value = "/login")
	public Result login(@RequestBody User user) throws Exception {
		try {
			JSONObject result = userLoginService.login(user);
			Object isauth = result.get("isauth");
			if (isauth != null) {
				Boolean isauthFlag = (Boolean) isauth;
				if (isauthFlag == false) {
					// 需跳转审核页面
					return Result.error(CodeMsg.ISAUTH_FALSE);
				} else {
					// 审核中
					return Result.error(CodeMsg.ISAUTH_TRUE);
				}
			}
			return Result.success(result);
		} catch (NotExistException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.NOT_EXIST);
		} catch (WrongPassException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.WRONG_PASS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 * 退出登录
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel/{token}")
	public Result cancel(@PathVariable String token) throws Exception {
		try {
			Object result = userLoginService.cancel(token);
			return Result.success(result);
		} catch (NotLoginException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.NOT_LOGIN);
		} catch (Exception e) {
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
			//为空证明mobile不存在
			//验证code
			String serverCode= redis.opsForValue().get(user.getMobile());
			if(serverCode==null||serverCode.equals("")) {
				//验证码不正确	public static CodeMsg CODE_EXCEPTION = new CodeMsg(500111,"验证码不正确");
				throw new CodeCheckException();
			}
			String code = user.getCode();
			if(!code.equals(serverCode)) {
				//验证码不正确	public static CodeMsg CODE_EXCEPTION = new CodeMsg(500111,"验证码不正确");
				 throw new CodeCheckException();
			}
			JSONObject result = userLoginService.register(user);
			userShareService.UserRegisterShareGetDiscount(user);
			return Result.success(result);
		}catch (PassCheckException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.PASS_CHECK);
		} catch (CodeCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.CODE_CHECK);
		} catch (MobileRepeatException e3) {
			logger.error(e3.getMessage(), e3);
			return Result.error(CodeMsg.MOBILE_REPEAT);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
		
	}
	
	
	

	

	/**
	 * 修改密码
	 */
	@ResponseBody
	@RequestMapping(value = "/changePassword")
	public Result changePassword(@RequestBody User user) throws Exception {
		try {
			Object result = userLoginService.changePassword(user);
			return Result.success(result);
		} catch (NotExistException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.NOT_EXIST);
		} catch (WrongPassException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.WRONG_PASS);
		} catch (PassCheckException e3) {
			logger.error(e3.getMessage(), e3);
			return Result.error(CodeMsg.PASS_CHECK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

	/**
	 * 找回密码
	 */
	@ResponseBody
	@RequestMapping(value = "/forgetPassword")
	public Result forgetPassword(@RequestBody User user) throws Exception {
		try {
			//验证code
			String serverCode= redis.opsForValue().get(user.getMobile());
			if(serverCode==null||serverCode.equals("")) {
				//验证码不正确	public static CodeMsg CODE_EXCEPTION = new CodeMsg(500111,"验证码不正确");
				throw new CodeCheckException();
			}
			String code = user.getCode();
			if(!code.equals(serverCode)) {
				//验证码不正确	public static CodeMsg CODE_EXCEPTION = new CodeMsg(500111,"验证码不正确");
				 throw new CodeCheckException();
			}
			Object result = userLoginService.forgetPassword(user);
			return Result.success(result);
		} catch (NotExistException e1) {
			logger.error(e1.getMessage(), e1);
			return Result.error(CodeMsg.NOT_EXIST);
		} catch (PassCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.PASS_CHECK);
		} catch (CodeCheckException e3) {
			logger.error(e3.getMessage(), e3);
			return Result.error(CodeMsg.CODE_CHECK);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

}
