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
import com.jeesite.modules.app.entity.Order;
import com.jeesite.modules.app.service.OrderService;

/**
 * sys_orderController
 * @author 范耘诚
 * @version 2019-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/app/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Order get(Long id, boolean isNewRecord) {
		return orderService.get(id.toString(), isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("app:order:view")
	@RequestMapping(value = {"list", ""})
	public String list(Order order, Model model) {
		model.addAttribute("order", order);
		return "modules/app/orderList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("app:order:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Order> listData(Order order, HttpServletRequest request, HttpServletResponse response) {
		order.setPage(new Page<>(request, response));
		Page<Order> page = orderService.findPage(order);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("app:order:view")
	@RequestMapping(value = "form")
	public String form(Order order, Model model) {
		model.addAttribute("order", order);
		return "modules/app/orderForm";
	}

	/**
	 * 保存sys_order
	 */
	@RequiresPermissions("app:order:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Order order) {
		orderService.save(order);
		return renderResult(Global.TRUE, text("保存sys_order成功！"));
	}
	
	/**
	 * 删除sys_order
	 */
	@RequiresPermissions("app:order:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Order order) {
		orderService.delete(order);
		return renderResult(Global.TRUE, text("删除sys_order成功！"));
	}
	
}