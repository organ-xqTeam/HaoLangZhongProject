package com.jeesite.modules.app.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tools.ant.types.resources.selectors.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.entity.UserShare;
import com.jeesite.modules.app.service.UserShareService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.RC4Util;
import com.jeesite.modules.app.utils.Result;

/**
 * 分享Controller
 * @author 范耘诚
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/shareController")
public class ShareController {
	@Autowired
	private UserShareService  userShareService;
	
	/**用户得到分享码*/
	/**
	 ** token    token     随便
	 * userId     用户id   123
	 * 展示当前用户的购物车列表
	 * /js/f/sys/shareController/getShareCode
	 * */
	@ResponseBody
	@RequestMapping(value = "/getShareCode",method=RequestMethod.POST)
	public  Result  getShareCode(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			String token = (String) requestParams.get("token");
			String userId= (String) requestParams.get("userId");
			//通过UserId 查找用户分享对象
			UserShare userShare = new  UserShare();
			userShare.setUserId(userId);
			List<UserShare>  userShareList= userShareService.findList(userShare);
			String  shareCode="";
			if(userShareList!=null&&userShareList.size()>=1) {
				//得到分享码
				shareCode=userShareList.get(0).getShareCode();
			}else {
				Map<String, Object> paramMap =new HashMap<>();
				paramMap.put("userId", userId);
				shareCode =RC4Util.getShareCode(userId);
				paramMap.put("userId", userId);
				paramMap.put("shareCode", shareCode);
				paramMap.put("createDate", new Date());
				//插入新的分享数据
				userShareService.insertUserShareMap(paramMap);
			}
			//输出分享码
			JSONObject result = new JSONObject();
			result.put("shareCode", shareCode);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}	
	}
	
	
 
}
