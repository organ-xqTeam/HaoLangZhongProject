/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.AirAdPic;
import com.jeesite.modules.app.service.AirAdPicService;

/**
 * sys_air_ad_picController
 * @author 范耘诚
 * @version 2019-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/app/airAdPic")
public class AirAdPicController extends BaseController {

	@Autowired
	private AirAdPicService airAdPicService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public AirAdPic get(Long id, boolean isNewRecord) {
		return airAdPicService.get(id.toString(), isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("app:airAdPic:view")
	@RequestMapping(value = {"list", ""})
	public String list(AirAdPic airAdPic, Model model) {
		model.addAttribute("airAdPic", airAdPic);
		return "modules/app/airAdPicList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("app:airAdPic:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<AirAdPic> listData(AirAdPic airAdPic, HttpServletRequest request, HttpServletResponse response) {
		airAdPic.setPage(new Page<>(request, response));
		Page<AirAdPic> page = airAdPicService.findPage(airAdPic);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("app:airAdPic:view")
	@RequestMapping(value = "form")
	public String form(AirAdPic airAdPic, Model model) {
		model.addAttribute("airAdPic", airAdPic);
		return "modules/app/airAdPicForm";
	}

	/**
	 * 保存sys_air_ad_pic
	 */
	@RequiresPermissions("app:airAdPic:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated AirAdPic airAdPic) {
		airAdPicService.save(airAdPic);
		return renderResult(Global.TRUE, text("保存sys_air_ad_pic成功！"));
	}
	
	/**
	 * 删除sys_air_ad_pic
	 */
	@RequiresPermissions("app:airAdPic:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(AirAdPic airAdPic) {
		airAdPicService.delete(airAdPic);
		return renderResult(Global.TRUE, text("删除sys_air_ad_pic成功！"));
	}
	
}