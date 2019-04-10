/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.web;

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
import com.jeesite.modules.app.entity.AirDrugCategory;
import com.jeesite.modules.app.entity.AirDrugComment;
import com.jeesite.modules.sys.entity.SysAirDrug;
import com.jeesite.modules.sys.service.SysAirDrugService;

/**
 * sys_air_drugController
 * @author 范耘诚
 * @version 2019-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysAirDrug")
public class SysAirDrugController extends BaseController {

	@Autowired
	private SysAirDrugService sysAirDrugService;
	private AirDrugComment AirDrugComment;
	/*private AirDrugCategory */
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SysAirDrug get(String id, boolean isNewRecord) {
		return sysAirDrugService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sys:sysAirDrug:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysAirDrug sysAirDrug, Model model) {
		model.addAttribute("sysAirDrug", sysAirDrug);
		return "modules/sys/sysAirDrugList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sys:sysAirDrug:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SysAirDrug> listData(SysAirDrug sysAirDrug, HttpServletRequest request, HttpServletResponse response) {
		sysAirDrug.setPage(new Page<>(request, response));
		Page<SysAirDrug> page = sysAirDrugService.findPage(sysAirDrug);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:sysAirDrug:view")
	@RequestMapping(value = "form")
	public String form(SysAirDrug sysAirDrug, Model model,HttpServletRequest request) {
		model.addAttribute("sysAirDrug", sysAirDrug);
		model.addAttribute("contextPath",request.getContextPath()); 
		return "modules/sys/sysAirDrugForm";
	}

	/**
	 * 保存sys_air_drug
	 */
	@RequiresPermissions("sys:sysAirDrug:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SysAirDrug sysAirDrug) {
		sysAirDrugService.save(sysAirDrug);
		
		return renderResult(Global.TRUE, text("保存sys_air_drug成功！"));
	}
	
	/**
	 * 删除sys_air_drug
	 */
	@RequiresPermissions("sys:sysAirDrug:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SysAirDrug sysAirDrug) {
		sysAirDrugService.delete(sysAirDrug);
		return renderResult(Global.TRUE, text("删除sys_air_drug成功！"));
	}
	
}