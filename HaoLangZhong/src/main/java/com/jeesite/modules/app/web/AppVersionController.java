package com.jeesite.modules.app.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.AppVersion;
import com.jeesite.modules.app.entity.HospitalInfo;
import com.jeesite.modules.app.service.AppVersionService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;
@Controller
@RequestMapping(value = "${frontPath}/app/appversion")
public class AppVersionController extends BaseController  {
	@Autowired
	private  AppVersionService appVersionService;
	/**
	 ** token    token     随便
	 *  type   0:安卓 , 1:苹果
	 *     展示好郎中医院的医院简介
	 * /js/f/app/appversion/getNewVersionByType
	 * */
	@RequestMapping(value = "/getNewVersionByType",method=RequestMethod.POST)
	public Result getNewVersionByType(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			String type=requestParams.get("type").toString();
			AppVersion appVersion =new AppVersion();
			appVersion.setType(type);
			List<AppVersion> appVersionList= appVersionService.findList(appVersion);
			JSONObject result = new JSONObject();
			if(appVersionList.size()>0) {
				result.put("appVersion", appVersionList.get(0));
			}
			
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

}
