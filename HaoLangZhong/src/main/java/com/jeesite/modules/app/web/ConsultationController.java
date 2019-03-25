package com.jeesite.modules.app.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.service.ConsultationService;
import com.jeesite.modules.app.service.DoctorLabelService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;

/**
 * 咨询信息管理Controller
 * @author 李昊翀
 * @version 2019-03-14
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/consultation")
public class ConsultationController extends BaseController {

	@Autowired
	private DoctorLabelService doctorLabelService;
	
	@Autowired
	private ConsultationService consultationService;

	/**
	 * 获取咨询信息详情
	 * */
	@ResponseBody
	@RequestMapping(value = "/findConsultationById/{id}")
	public Result findConsultationById(@PathVariable String id) {
		try {
			JSONObject resultJson = new JSONObject();
			Map<String, Object> consultation = consultationService.findConsultationById(id);
			String body = consultation.get("body").toString();
			String therapy = consultation.get("therapy").toString();
			String [] bodys = body.split(",");
			String [] therapys = therapy.split(",");
			List<String> bodyArr = new ArrayList<String>();
			List<String> therapyArr = new ArrayList<String>();
			for(String s : bodys) {
				bodyArr.add(s);
			}
			for(String s : therapys) {
				therapyArr.add(s);
			}
			resultJson.put("consultation", consultation);
			resultJson.put("pics", consultationService.findConsultationPic(consultation.get("id").toString()));
			resultJson.put("body", doctorLabelService.queryListByIds(bodyArr));
			resultJson.put("therapys", doctorLabelService.queryListByIds(therapyArr));
			return Result.success(resultJson);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
}
