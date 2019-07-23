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
import com.jeesite.modules.sys.entity.SysMemberOrder;
import com.jeesite.modules.sys.service.SysMemberOrderService;

/**
 * sys_member_orderController
 * @author 范耘诚
 * @version 2019-07-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysMemberOrder")
public class SysMemberOrderController extends BaseController {

	@Autowired
	private SysMemberOrderService sysMemberOrderService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SysMemberOrder get(String id, boolean isNewRecord) {
		return sysMemberOrderService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sys:sysMemberOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysMemberOrder sysMemberOrder, Model model) {
		model.addAttribute("sysMemberOrder", sysMemberOrder);
		return "modules/sys/sysMemberOrderList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sys:sysMemberOrder:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SysMemberOrder> listData(SysMemberOrder sysMemberOrder, HttpServletRequest request, HttpServletResponse response) {
		sysMemberOrder.setPage(new Page<>(request, response));
		Page<SysMemberOrder> page = sysMemberOrderService.findPage(sysMemberOrder);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:sysMemberOrder:view")
	@RequestMapping(value = "form")
	public String form(SysMemberOrder sysMemberOrder, Model model) {
		model.addAttribute("sysMemberOrder", sysMemberOrder);
		return "modules/sys/sysMemberOrderForm";
	}

	/**
	 * 保存sys_member_order
	 */
	@RequiresPermissions("sys:sysMemberOrder:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SysMemberOrder sysMemberOrder) {
		sysMemberOrderService.save(sysMemberOrder);
		return renderResult(Global.TRUE, text("保存sys_member_order成功！"));
	}
	
	/**
	 * 删除sys_member_order
	 */
	@RequiresPermissions("sys:sysMemberOrder:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SysMemberOrder sysMemberOrder) {
		sysMemberOrderService.delete(sysMemberOrder);
		return renderResult(Global.TRUE, text("删除sys_member_order成功！"));
	}
	
}