/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.web;

import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.dao.DoctorLabelDao;
import com.jeesite.modules.app.entity.AirDrugCategory;
import com.jeesite.modules.app.entity.AirDrugComment;
import com.jeesite.modules.app.entity.AirDrugInventory;
import com.jeesite.modules.app.entity.AirDrugRelationCategory;
import com.jeesite.modules.app.service.AirDrugCategoryService;
import com.jeesite.modules.app.service.AirDrugCommentSercvie;
import com.jeesite.modules.app.service.AirDrugInventoryService;
import com.jeesite.modules.app.service.AirDrugRelationCategoryService;
import com.jeesite.modules.app.service.AirDrugService;
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
	@Autowired
	private AirDrugCommentSercvie airDrugCommentSercvie;
	@Autowired
	private AirDrugInventoryService    airDrugInventoryService;
	@Autowired
	private AirDrugCategoryService   airDrugCategoryService;
	@Autowired
	private AirDrugRelationCategoryService  airDrugRelationCategoryService;
	@Autowired
	private DoctorLabelDao doctorLabelDao;
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
		sysAirDrug=sysAirDrugService.get(sysAirDrug);
		model.addAttribute("sysAirDrug", sysAirDrug);
		model.addAttribute("contextPath",request.getContextPath());
		AirDrugCategory airDrugCategory =new AirDrugCategory();
		List<AirDrugCategory> airDrugCategoryList= airDrugCategoryService.findList(airDrugCategory);
		model.addAttribute("airDrugCategoryList",airDrugCategoryList);
		
		if(sysAirDrug.getDrugInventoryId()!=null) {
			AirDrugInventory drugInventory =new AirDrugInventory();
			drugInventory.setId(sysAirDrug.getDrugInventoryId().toString());
			drugInventory=airDrugInventoryService.get(drugInventory);
			model.addAttribute("drugCount",drugInventory.getDrugCount());
		}
		//得到此药品的关系类别对象
		if(sysAirDrug.getId()!=null) {
			AirDrugRelationCategory airDrugRelationCategory =new AirDrugRelationCategory();
			airDrugRelationCategory.setDrugId(Long.valueOf(sysAirDrug.getId()));
			List<AirDrugRelationCategory> airDrugRelationCategoryList= airDrugRelationCategoryService.findList(airDrugRelationCategory);
			if(airDrugRelationCategoryList!=null&&airDrugRelationCategoryList.size()>0) {
				model.addAttribute("categoryId",airDrugRelationCategoryList.get(0).getCategoryId());
				model.addAttribute("airDrugRelationCategory",airDrugRelationCategoryList.get(0));
			}
		}
		return "modules/sys/sysAirDrugForm";
	}

	/**
	 *
	 *  保存sys_air_drug
	 * 
	 */
	@RequiresPermissions("sys:sysAirDrug:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SysAirDrug sysAirDrug,String categoryId,String drugCount,String airDrugRelationCategoryId) {
		String id= sysAirDrug.getId();
		System.out.println(id);
		if(id==null) {
			System.out.println(categoryId);
			AirDrugInventory airDrugInventory =new AirDrugInventory();
			airDrugInventory.setDrugCount(drugCount);
			airDrugInventoryService.save(airDrugInventory);
			System.out.println(airDrugInventory);
			System.out.println(airDrugInventory.getId());
			sysAirDrug.setDrugInventoryId(Long.valueOf(airDrugInventory.getId()));
			sysAirDrugService.save(sysAirDrug);
			System.out.println(sysAirDrug.getId());
			String drugId=sysAirDrug.getId();
			//关系表
			AirDrugRelationCategory airDrugRelationCategory =new AirDrugRelationCategory();
			airDrugRelationCategory.setCategoryId(Long.valueOf(categoryId));
			airDrugRelationCategory.setDrugId(Long.valueOf(drugId));
			airDrugRelationCategory.setCreateDate(new Date());
			airDrugRelationCategoryService.insert(airDrugRelationCategory);
		}else {
			sysAirDrugService.update(sysAirDrug);	
			AirDrugInventory airDrugInventory =new AirDrugInventory();
			airDrugInventory.setId(sysAirDrug.getDrugInventoryId().toString());
			airDrugInventory.setDrugCount(drugCount);
			airDrugInventoryService.update(airDrugInventory);
			String drugId=sysAirDrug.getId();
			AirDrugRelationCategory airDrugRelationCategory =new AirDrugRelationCategory();
			airDrugRelationCategory.setId(airDrugRelationCategoryId);
			airDrugRelationCategory.setDrugId(Long.valueOf(drugId));
			airDrugRelationCategory.setCategoryId( Long.valueOf(categoryId));
			airDrugRelationCategoryService.update(airDrugRelationCategory);
		}
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