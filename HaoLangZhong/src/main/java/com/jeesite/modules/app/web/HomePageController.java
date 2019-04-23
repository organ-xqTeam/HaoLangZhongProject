package com.jeesite.modules.app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.HomePage;
import com.jeesite.modules.app.service.HomePageService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;

/**
 * 首页广告管理Controller
 * @author 范耘诚
 * @version 2019-03-01
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/homePage")
public class HomePageController extends BaseController  {
	@Autowired
	private  HomePageService homePageService;
	
	
	/**
	 * 获取医生标签列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/getHomePage")
	public Result getHomePage() {
		try {
			HomePage  homePage =new HomePage();
			homePage.setDelFlag("0");
			List<HomePage>  homePageList= homePageService.findList(homePage);
			if(homePageList!=null&&homePageList.size()>0) {
				homePage=homePageList.get(0);
			}
			return Result.success(homePage);			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

}
