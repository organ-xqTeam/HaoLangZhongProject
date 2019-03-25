package com.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.service.DoctorLabelService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;

/**
 * 医生标签管理Controller
 * @author 李昊翀
 * @version 2019-03-01
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/doctorLabel")
public class DoctorLabelController extends BaseController {
	
	@Autowired
	private DoctorLabelService doctorLabelService;
	
	/**
	 * 获取医生标签列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryList/{type}")
	public Result queryList(@PathVariable String type) {
		try {
			return Result.success(doctorLabelService.queryList(type));			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

}
