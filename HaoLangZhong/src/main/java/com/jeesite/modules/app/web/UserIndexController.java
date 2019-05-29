package com.jeesite.modules.app.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.AirDrug;
import com.jeesite.modules.app.entity.UserCollection;
import com.jeesite.modules.app.service.AirDrugService;
import com.jeesite.modules.app.service.UserIndexService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.GetLocationBaiduMap;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.TokenTools;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
 * 用户首页加载信息controller
 * */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/userIndex")
public class UserIndexController extends BaseController {
	
	@Autowired
	private UserIndexService userIndexService;
	
	@Autowired
	private AirDrugService airDrugService;
	
	/**
	 * 加载首页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/getData")
	public Result getData() {
		try {
			JSONObject result = new JSONObject();			
			// 首页获取空中药房
			AirDrug airDrug =new AirDrug();
			airDrug.setDelFlag("0");
			airDrug.setFirstFlag("1");
			airDrug.setLimit("1");
			//热销优先显示
			List<Map> airDrugFirstList=  airDrugService.getAirDrugFirstList(airDrug);
			result.put("doctor", userIndexService.queryDoctorList());
			result.put("banner", userIndexService.queryBannerList());
			result.put("airDrug", airDrugFirstList);
			
			return Result.success(result);			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/**
	 * 通过经纬度查询城市名
	 * */
	@ResponseBody
	@RequestMapping(value = "/getCity")
	public Result getCity(@RequestBody Map<String, Object> requestParams) {
		try {
			String  latitude=(String) requestParams.get("latitude");
			String  longitude=(String) requestParams.get("longitude");
			JSONObject result = new JSONObject();	
			String city=GetLocationBaiduMap.getCity(latitude, longitude);
			result.put("city", city);
			return Result.success(result);			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/*
	 * /sys/userIndex/video
	 */
	@RequestMapping("/video")
	public @ResponseBody void video(String id, HttpServletResponse response)throws Exception{
		File file = new File("D:/1.mp4");
		FileInputStream in = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();
		byte[] b = null;
		while(in.available() >0) {
			if(in.available()>10240) {
				b = new byte[10240];
			}else {
				b = new byte[in.available()];
			}
			in.read(b, 0, b.length);
			out.write(b, 0, b.length);
		}
		in.close();
		out.flush();
		out.close();
	}


	

}
