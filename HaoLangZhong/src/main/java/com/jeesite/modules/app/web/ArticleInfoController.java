package com.jeesite.modules.app.web;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.ArticleInfo;
import com.jeesite.modules.app.service.ArticleInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.app.utils.Result;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/articleInfo")
public class ArticleInfoController extends BaseController {
	
	@Autowired
	private ArticleInfoService articleInfoService;
	
	@ResponseBody
	@RequestMapping(value = "/queryList")
	public Result queryList(@RequestBody ArticleInfo requestParams) {
		try {
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = articleInfoService.queryList(requestParams);
			JSONObject result = new JSONObject();
			
			result.put("items", resultList);
			result.put("count", articleInfoService.queryCount(requestParams));
			return Result.success(result);		
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 查询文章的详细信息
	 * */
	@ResponseBody
	@RequestMapping(value = "/findOneById/{id}")
	public Result findOneById(@PathVariable String id) {
		try {			
			return Result.success(articleInfoService.findOneById(id));
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

}
