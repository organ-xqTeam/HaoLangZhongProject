package com.jeesite.modules.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.entity.AreaInfo;
import com.jeesite.modules.app.entity.ArticleInfo;
import com.jeesite.modules.app.service.AreaInfoService;
import com.jeesite.modules.app.service.ArticleInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/area")
public class AreaInfoController {
	@Autowired
	private AreaInfoService areaInfoService;
	
	
	@ResponseBody
	@RequestMapping(value = "/getAreaInfo")
	public Result getAreaInfo(@RequestBody Map<String, Object> requestParams) {
		try {
			AreaInfo areaInfo =new AreaInfo();
			areaInfo.setArealevel(2);
			List<AreaInfo> areaInfoList= areaInfoService.findList(areaInfo);
			JSONObject result = new JSONObject();
			result.put("areaInfoList", areaInfoList);
			return Result.success(result);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
		
	}
	@ResponseBody
	@RequestMapping(value = "/getArea")
	public Result getArea(@RequestBody Map<String, Object> requestParams) {
		try {
			String arealevel= (String) requestParams.get("arealevel");
			AreaInfo areaInfo =new AreaInfo();
			if(arealevel.equals("1")) {
				areaInfo.setArealevel(1);
			}else if(arealevel.equals("2")) {
				String parentId= (String) requestParams.get("parentId");
				areaInfo.setArealevel(2);
				areaInfo.setParentId(Long.valueOf(parentId));
			}
			List<AreaInfo> areaInfoList= areaInfoService.findList(areaInfo);
			JSONObject result = new JSONObject();
			result.put("areaInfoList", areaInfoList);
			return Result.success(result);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
		
	}
	@ResponseBody
	@RequestMapping(value = "/getGradeArea")
	public Result getGradeArea(@RequestBody Map<String, Object> requestParams) {
		try {
			//String arealevel= (String) requestParams.get("arealevel");
			AreaInfo areaInfo =new AreaInfo();
			areaInfo.setArealevel(1);
			List<AreaInfo> areaInfoParentList= areaInfoService.findList(areaInfo);
			areaInfo.setArealevel(2);
			List<AreaInfo> areaInfoChildList= areaInfoService.findList(areaInfo);
			
			for (int i = 0; i < areaInfoParentList.size(); i++) {
				List<Object> childList=new ArrayList<>();
				for (int j = 0; j < areaInfoChildList.size(); j++) {
					if(areaInfoParentList.get(i).getId().equals(String.valueOf(areaInfoChildList.get(j).getParentId()))) {
						childList.add(areaInfoChildList.get(j));
					}

					areaInfoParentList.get(i).setChild(childList);
				}
				
			}
			JSONObject result = new JSONObject();
			result.put("areaInfoList", areaInfoParentList);
			return Result.success(result);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
		
	}

}
