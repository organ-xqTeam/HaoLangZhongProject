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
import com.jeesite.modules.sys.entity.SysTiaoliOrder;
import com.jeesite.modules.sys.service.SysTiaoliOrderService;

/**
 * sys_tiaoli_orderController
 * @author 范耘诚
 * @version 2019-07-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysTiaoliOrder")
public class SysTiaoliOrderController extends BaseController {

	@Autowired
	private SysTiaoliOrderService sysTiaoliOrderService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SysTiaoliOrder get(String id, boolean isNewRecord) {
		SysTiaoliOrder sysTiaoliOrder= sysTiaoliOrderService.get(id, isNewRecord);
		return sysTiaoliOrder;
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sys:sysTiaoliOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysTiaoliOrder sysTiaoliOrder, Model model) {
		model.addAttribute("sysTiaoliOrder", sysTiaoliOrder);
		return "modules/sys/sysTiaoliOrderList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sys:sysTiaoliOrder:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SysTiaoliOrder> listData(SysTiaoliOrder sysTiaoliOrder, HttpServletRequest request, HttpServletResponse response) {
		sysTiaoliOrder.setPage(new Page<>(request, response));
		Page<SysTiaoliOrder> page = sysTiaoliOrderService.findPage(sysTiaoliOrder);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:sysTiaoliOrder:view")
	@RequestMapping(value = "form")
	public String form(SysTiaoliOrder sysTiaoliOrder, Model model) {
		model.addAttribute("sysTiaoliOrder", sysTiaoliOrder);
		return "modules/sys/sysTiaoliOrderForm";
	}

	/**
	 * 保存sys_tiaoli_order
	 */
	@RequiresPermissions("sys:sysTiaoliOrder:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SysTiaoliOrder sysTiaoliOrder) {
		sysTiaoliOrderService.save(sysTiaoliOrder);
		return renderResult(Global.TRUE, text("保存sys_tiaoli_order成功！"));
	}
	
	/**
	 * 删除sys_tiaoli_order
	 */
	@RequiresPermissions("sys:sysTiaoliOrder:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SysTiaoliOrder sysTiaoliOrder) {
		sysTiaoliOrderService.delete(sysTiaoliOrder);
		return renderResult(Global.TRUE, text("删除sys_tiaoli_order成功！"));
	}
	
}