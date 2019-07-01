package com.jeesite.modules.app.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.service.UserInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;

/**
 * 会员服务controller
 * @author 1111111
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/app/member")
public class MemberController extends BaseController {
	
	
	@Autowired
	private UserInfoService userInfoService; 
	
	
	/**
	 * 查看此用户是否为会员Controller
	 * /app/isMemberByUserId
	 */
	@RequestMapping(value = "/isMemberByUserId",method=RequestMethod.POST)
	@ResponseBody
	public Result IsMemberByUserId(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		String userId=requestParams.get("userId").toString();
		String token=requestParams.get("token").toString();
		
		UserInfo userInfo =new UserInfo();
		userInfo.setId(userId);
		userInfo=userInfoService.get(userInfo);
		String memberFlag= userInfo.getMemberFlag();
		if(memberFlag==null) {
			//不是会员
			return Result.success(CodeMsg.MEMBER_FALSE);
		}
		if(memberFlag.equals("0")) {
			//不是会员
			return Result.success(CodeMsg.MEMBER_FALSE);
		}else if(memberFlag.equals("1")) {
			Long memberStartTime= userInfo.getMemberStart().getTime();
			Long memberEndTime=userInfo.getMemberEnd().getTime();
			Long nowDateTime =(new Date()).getTime();
			if(memberEndTime<nowDateTime) {
				//会员过期
				return Result.success(CodeMsg.MEMBER_EXPIRE);
			}
		}
		//是会员
		return Result.success(CodeMsg.MEMBER_TRUE) ;
	}
	
	
	
	//会员支付操作
	@RequestMapping(value = "/buyMember",method=RequestMethod.POST)
	@ResponseBody
	public Result BuyMember(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		String userId=requestParams.get("userId").toString();
		String token=requestParams.get("token").toString();
		UserInfo userInfo =new UserInfo();
		userInfo.setId(userId);
		userInfo=userInfoService.get(userInfo);
		return null;
	}
	
	

}
