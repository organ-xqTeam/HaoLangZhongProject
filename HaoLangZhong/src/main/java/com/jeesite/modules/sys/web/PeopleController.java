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

import com.alibaba.druid.sql.dialect.phoenix.parser.PhoenixExprParser;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.entity.People;
import com.jeesite.modules.sys.entity.PeopleDet;
import com.jeesite.modules.sys.service.PeopleService;

/**
 * peopleController
 * @author 范耘诚
 * @version 2019-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/people")
public class PeopleController extends BaseController {

	@Autowired
	private PeopleService peopleService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public People get(String id, boolean isNewRecord) {
		People people=  peopleService.get(id, isNewRecord);
		List<PeopleDet> peopleDetList= people.getPeopleDetList();
		return people;
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sys:people:view")
	@RequestMapping(value = {"list", ""})
	public String list(People people, Model model) {
		model.addAttribute("people", people);
		return "modules/sys/peopleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sys:people:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<People> listData(People people, HttpServletRequest request, HttpServletResponse response) {
		people.setPage(new Page<>(request, response));
		Page<People> page = peopleService.findPage(people);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:people:view")
	@RequestMapping(value = "form")
	public String form(People people, Model model) {
		model.addAttribute("people", people);
		return "modules/sys/peopleForm";
	}

	/**
	 * 保存people
	 */
	@RequiresPermissions("sys:people:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated People people) {
		peopleService.save(people);
		return renderResult(Global.TRUE, text("保存people成功！"));
	}
	
	/**
	 * 删除people
	 */
	@RequiresPermissions("sys:people:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(People people) {
		peopleService.delete(people);
		return renderResult(Global.TRUE, text("删除people成功！"));
	}
	
}