package com.jeesite.modules.app.web;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.service.DoctorCommentService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.TokenTools;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
 * 医生评论管理Controller
 * @author 李昊翀
 * @version 2019-02-21
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/doctorComment")
public class DoctorCommentController extends BaseController {

	@Autowired
	private DoctorCommentService doctorCommentService;
	
	@Autowired
	private StringRedisTemplate redis;
	
	/**
	 * 提交评论
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveDoctorComment")
	public Result saveDoctorComment(@RequestBody Map<String, Object> requestMap) {
		try {
			/*TokenTools.checkToken(requestMap.get("token").toString(), redis);*/
			doctorCommentService.saveDoctorComment(requestMap);
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
