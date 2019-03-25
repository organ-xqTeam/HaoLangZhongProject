/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.sys.entity.SysUserInfo;
import com.jeesite.modules.sys.service.SysUserInfoService;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * sys_user_infoController
 * @author a
 * @version 2019-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysUserInfo")
public class SysUserInfoController extends BaseController {

	@Autowired
	private SysUserInfoService sysUserInfoService;
		
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysUserInfo sysUserInfo, Model model) {
		model.addAttribute("sysUserInfo", sysUserInfo);
		return "modules/sys/sysUserInfoList";
	}
	
	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = "add")
	public String form(Model model) {
		SysUserInfo s = new SysUserInfo();
		model.addAttribute("sysUserInfo", s);
		model.addAttribute("isNewRecord", true);
		return "modules/sys/sysUserInfoForm";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SysUserInfo> listData(@ModelAttribute SysUserInfo sysUserInfo, HttpServletRequest request, HttpServletResponse response) {	
		Page<SysUserInfo> page = new Page<>(request, response);
		try {
			PageModel pageModel = new PageModel(page.getPageNo(), page.getPageSize());
			sysUserInfo.setPageModel(pageModel);
			if (page.getOrderBy() != null) {
				sysUserInfo.setOrderBy("order by " + page.getOrderBy());
			}
			else {
				sysUserInfo.setOrderBy(null);
			}
			sysUserInfoService.findPage(sysUserInfo, page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return page;
	}
	
	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = "edit")
	public String form(@RequestParam("id") String id, Model model) {
		SysUserInfo s = new SysUserInfo();
		model.addAttribute("sysUserInfo", s);
		
		model.addAttribute("isNewRecord", false);
		return "modules/sys/sysUserInfoForm";
	}

	/**
	 * 保存sys_user_info
	 */
	@RequiresPermissions("sys:sysUserInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SysUserInfo sysUserInfo) {
		sysUserInfoService.save(sysUserInfo);
		return renderResult(Global.TRUE, text("保存sys_user_info成功！"));
	}
	
	/**
	 * 删除sys_user_info
	 */
	@RequiresPermissions("sys:sysUserInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(@RequestParam("id") String id) {
		sysUserInfoService.delete(id);
		return renderResult(Global.TRUE, text("删除医生成功！"));
	}
	
}