package com.jeesite.modules.app.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.Message;
import com.jeesite.modules.app.entity.User;
import com.jeesite.modules.app.service.MessageService;
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
@RequestMapping(value = "${frontPath}/sys/message")
public class MessageController extends BaseController {

	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private UserShareService userShareService;
	@Autowired
	private MessageService messageService;
	
	/**message/getMessageList"
	 * 用户登录
	 * */
	@ResponseBody
	@RequestMapping(value = "/getMessageList")
	public Result getMessageList(@RequestBody Map<String, Object> requestParams) throws Exception {
		try {
			/*JSONObject result = userLoginService.login(user);*/
			Message message= new Message();
			List<Message> messageList= messageService.findList(message);
			return Result.success(messageList);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/***
	 * message/getMessageById
	 */
	@ResponseBody
	@RequestMapping(value = "/getMessageById")
	public Result getMessageById(@RequestBody Map<String, Object> requestParams) throws Exception {
		try {
			/*JSONObject result = userLoginService.login(user);*/
			
			String id =(String) requestParams.get("id");
			Message message= new Message();
			message.setId(id);
			message= messageService.get(message);
			return Result.success(message);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}	
	
	
}
