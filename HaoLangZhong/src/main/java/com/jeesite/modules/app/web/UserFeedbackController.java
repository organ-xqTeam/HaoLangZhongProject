package com.jeesite.modules.app.web;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.service.UserFeedbackService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.DateUtil;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.TokenTools;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
 * 用户反馈controller
 * */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/userFeedback")
public class UserFeedbackController extends BaseController {

	@Autowired
	private UserFeedbackService userFeedbackService;
	
	@Autowired
	private StringRedisTemplate redis;
	
	/**
	 * 用户提交反馈
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveFeedback")
	public Result saveFeedback(@RequestBody Map<String, Object> requestParams) {
		try {
			TokenTools.checkToken(requestParams.get("token").toString(), redis);
			requestParams.put("create_date", DateUtil.getSysTime1());
			requestParams.put("del_flag", "0");
			userFeedbackService.saveFeedback(requestParams);
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
	
}
