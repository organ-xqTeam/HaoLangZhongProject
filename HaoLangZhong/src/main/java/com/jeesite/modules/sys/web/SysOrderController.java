/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.web;

import java.util.List;

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
import com.jeesite.modules.sys.entity.SysAirDrug;
import com.jeesite.modules.sys.entity.SysOrder;
import com.jeesite.modules.sys.entity.SysOrderDetail;
import com.jeesite.modules.sys.service.SysAirDrugService;
import com.jeesite.modules.sys.service.SysOrderDetailService;
import com.jeesite.modules.sys.service.SysOrderService;

/**
 * sys_orderController
 * @author 范耘诚
 * @version 2019-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysOrder")
public class SysOrderController extends BaseController {

	@Autowired
	private SysOrderService sysOrderService;
	@Autowired
	private SysAirDrugService  sysAirDrugService;
	@Autowired
	private SysOrderDetailService sysOrderDetailService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SysOrder get(String id, boolean isNewRecord) {
		SysOrder sysOrder= sysOrderService.get(id, isNewRecord);
		SysOrderDetail sysOrderDetail =new SysOrderDetail();
		if(sysOrder.getId()!=null) {
			sysOrderDetail.setOrderId(Long.valueOf(sysOrder.getId()));
			sysOrderDetail.setSysOrderId(sysOrder);
			List<SysOrderDetail>   sysOrderDetailList=sysOrderDetailService.findList(sysOrderDetail);
			/*for (SysOrderDetail sysOrderDetails : sysOrderDetailList) {
				SysAirDrug sysAirDrug =new SysAirDrug();
				sysAirDrug.setId(sysOrderDetails.getGrudId().toString());
				sysAirDrug=sysAirDrugService.get(sysAirDrug);
				sysOrderDetails.setSysAirDrug(sysAirDrug);
			}*/
			sysOrder.setSysOrderDetailList(sysOrderDetailList);
		}
		return sysOrder;
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sys:sysOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysOrder sysOrder, Model model) {
		model.addAttribute("sysOrder", sysOrder);
		return "modules/sys/sysOrderList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sys:sysOrder:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SysOrder> listData(SysOrder sysOrder, HttpServletRequest request, HttpServletResponse response) {
		sysOrder.setPage(new Page<>(request, response));
		Page<SysOrder> page = sysOrderService.findPage(sysOrder);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:sysOrder:view")
	@RequestMapping(value = "form")
	public String form(SysOrder sysOrder, Model model) {
		model.addAttribute("sysOrder", sysOrder);
		return "modules/sys/sysOrderForm";
	}

	/**
	 * 保存sys_order
	 */
	@RequiresPermissions("sys:sysOrder:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SysOrder sysOrder) {
		sysOrderService.save(sysOrder);
		return renderResult(Global.TRUE, text("保存sys_order成功！"));
	}
	
	/**
	 * 删除sys_order
	 */
	@RequiresPermissions("sys:sysOrder:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SysOrder sysOrder) {
		sysOrderService.delete(sysOrder);
		return renderResult(Global.TRUE, text("删除sys_order成功！"));
	}
	
}