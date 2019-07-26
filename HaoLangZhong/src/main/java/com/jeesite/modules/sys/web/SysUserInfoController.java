/**
 * Copyright (c) 2013- Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.web;

import java.util.ArrayList;
import java.util.Date;
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
import com.jeesite.modules.app.dao.DoctorLabelDao;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.sys.entity.SysDoctorInfo;
import com.jeesite.modules.sys.entity.SysDoctorPic;
import com.jeesite.modules.sys.entity.SysUserInfo;
import com.jeesite.modules.sys.service.SysDoctorInfoService;
import com.jeesite.modules.sys.service.SysDoctorPicService;
import com.jeesite.modules.sys.service.SysUserInfoService;

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
	@Autowired
	private  SysDoctorPicService sysDoctorPicService;
	
	@Autowired
	private  DoctorLabelDao  doctorLabelDao;
	@Autowired
    private SysDoctorInfoService  sysDoctorInfoService;
	
	/**
	 * 跳转普通注册信息列表
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = {"commonList", ""})
	public String commonList(SysUserInfo sysUserInfo, Model model) {
		model.addAttribute("sysUserInfo", sysUserInfo);
		return "modules/sys/sysCommonUserInfoList";
	}
	/**
	 * 查询普通注册信息列表数据
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = "commonListData")
	@ResponseBody
	public Page<SysUserInfo> commonListData(@ModelAttribute SysUserInfo sysUserInfo, HttpServletRequest request, HttpServletResponse response) {	
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
			sysUserInfo.setType("1");
			sysUserInfoService.findCommonPage(sysUserInfo, page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return page;
	}
		
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
		List<Map> isauthenticationList =new ArrayList<>();
		//isauthenticationList.add(e)
		SysUserInfo s = new SysUserInfo();
		if(id!=null) {
			s.setId(id);
			s=sysUserInfoService.get(s);
		}
		SysDoctorPic sysDoctorPic =new SysDoctorPic();
		sysDoctorPic.setDoctorid(id);
		List<SysDoctorPic> sysDoctorPicList= sysDoctorPicService.findList(sysDoctorPic);
		
		model.addAttribute("sysUserInfo", s);
		System.out.println(sysDoctorPicList.size());
		if(sysDoctorPicList!=null&&sysDoctorPicList.size()>0) {
			model.addAttribute("sysDoctorPic", sysDoctorPicList.get(0));
		}
		//获取星级
		SysDoctorInfo sysDoctorInfo =new SysDoctorInfo();
		sysDoctorInfo.setDoctorid(id);
		List<SysDoctorInfo> SysDoctorInfoList=sysDoctorInfoService.findList(sysDoctorInfo);
		if(SysDoctorInfoList!=null&&SysDoctorInfoList.size()>0) {
			model.addAttribute("starLv", SysDoctorInfoList.get(0).getStarLv());
		}
		
		model.addAttribute("isNewRecord", false);
		return "modules/sys/sysUserInfoForm";
	}
	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = "commonEdit")
	public String commonForm(@RequestParam("id") String id, Model model) {
		List<Map> isauthenticationList =new ArrayList<>();
		//isauthenticationList.add(e)
		SysUserInfo s = new SysUserInfo();
		if(id!=null) {
			s.setId(id);
			s=sysUserInfoService.get(s);
		}
		model.addAttribute("sysUserInfo", s);
		model.addAttribute("isNewRecord", false);
		return "modules/sys/sysCommonUserInfoForm";
	}

	/**
	 * 保存sys_user_info
	 */
	@RequiresPermissions("sys:sysUserInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SysUserInfo sysUserInfo,String pass,String starLv) {
		System.out.println(pass);
		if(pass.equals("1")) {
			//只修改通过与否
			SysUserInfo updateSysUserInfo =new SysUserInfo();
			updateSysUserInfo.setId(sysUserInfo.getId());
			System.out.println(sysUserInfo.getIsauthentication());
			updateSysUserInfo.setIsauthentication(sysUserInfo.getIsauthentication());
			updateSysUserInfo.setUpdateDate(new Date());
			//sysUserInfoService.save(updateSysUserInfo);
			sysUserInfoService.update(updateSysUserInfo);
			//修改星级
			SysDoctorInfo sysDoctorInfo =new SysDoctorInfo();
			sysDoctorInfo.setDoctorid(sysUserInfo.getId());
			sysDoctorInfo=sysDoctorInfoService.findList(sysDoctorInfo).get(0);
			sysDoctorInfo.setStarLv(starLv);
			sysDoctorInfoService.update(sysDoctorInfo);
		}else {
			
			sysUserInfoService.save(sysUserInfo);
		}
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-------------------------------------------------------
	/**
	 * 获取数据
	 */
	/*@ModelAttribute
	public SysUserInfo get(String id, boolean isNewRecord) {
		return sysUserInfoService.get(id, isNewRecord);
	}
	*/
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = {"userlist", ""})
	public String userlist(SysUserInfo sysUserInfo, Model model) {
		SysUserInfo s = new SysUserInfo();
		
		model.addAttribute("sysUserInfo", s);
		return "modules/sys/sysUserInfoListUser";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = "userlistData")
	@ResponseBody
	public Page<SysUserInfo> userlistData(SysUserInfo sysUserInfo, HttpServletRequest request, HttpServletResponse response) {
		sysUserInfo.setPage(new Page<>(request, response));
		sysUserInfo.setType("1");
		Page<SysUserInfo> page = sysUserInfoService.findPage(sysUserInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = "userform")
	public String userform(SysUserInfo sysUserInfo, Model model) {
		if(sysUserInfo.getId()!=null) {
			sysUserInfo.setId(sysUserInfo.getId());
			
			sysUserInfo=sysUserInfoService.get(sysUserInfo);
		}
		model.addAttribute("sysUserInfo", sysUserInfo);
		return "modules/sys/sysUserInfoFormUser";
	}


	/**
	 * 保存sys_user_info
	 */
	@RequiresPermissions("sys:sysUserInfo:edit")
	@PostMapping(value = "usersave")
	@ResponseBody
	public String usersave(@Validated SysUserInfo sysUserInfo) {
		sysUserInfoService.save(sysUserInfo);
		return renderResult(Global.TRUE, text("保存sys_user_info成功！"));
	}
	
	/**
	 * 删除sys_user_info
	 */
	@RequiresPermissions("app:sysUserInfo:edit")
	@RequestMapping(value = "userdelete")
	@ResponseBody
	public String userdelete(SysUserInfo sysUserInfo) {
		sysUserInfoService.delete(sysUserInfo);
		return renderResult(Global.TRUE, text("删除sys_user_info成功！"));
	}
	/**
	 * 分享得用户列表
	 */
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = {"userlistShare", ""})
	public String userlistShare(SysUserInfo sysUserInfo, Model model) {
		SysUserInfo s = new SysUserInfo();
		model.addAttribute("sysUserInfo", s);
		return "modules/sys/sysUserInfoListUsershare";
	}
	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sys:sysUserInfo:view")
	@RequestMapping(value = "sysUserInfoFormUserShare")
	public String sysUserInfoFormUserShare(SysUserInfo sysUserInfo, Model model) {
		if(sysUserInfo.getId()!=null) {
			sysUserInfo.setId(sysUserInfo.getId());
			
			sysUserInfo=sysUserInfoService.get(sysUserInfo);
		}
		List<SysUserInfo> sysUserInfoAllList= sysUserInfoService.findList(new SysUserInfo());
		//一级分销得list
		List<SysUserInfo> sysUserInfoOneShareList=new ArrayList<>();
		for (SysUserInfo sysUserInfoitem : sysUserInfoAllList) {
			String inviteUserId = sysUserInfoitem.getInviteUserId();
			if(inviteUserId!=null) {
				if(sysUserInfo.getId().equals(inviteUserId)) {
					sysUserInfoOneShareList.add(sysUserInfoitem);
				}
			}
		}
		//二级分销得list
		List<SysUserInfo> sysUserInfoTwoShareList=new ArrayList<>();
		for (SysUserInfo sysUserInfoOneShareItem : sysUserInfoOneShareList) {
			for (SysUserInfo sysUserInfoitem : sysUserInfoAllList) {
				String inviteUserId =sysUserInfoitem.getInviteUserId();
				if(inviteUserId!=null) {
					if(sysUserInfoOneShareItem.getId().equals(inviteUserId)) {
						sysUserInfoTwoShareList.add(sysUserInfoitem);
					}
				}
			}
		}
		
		
		
		model.addAttribute("sysUserInfo", sysUserInfo);
		model.addAttribute("sysUserInfoOneShareList", sysUserInfoOneShareList);
		model.addAttribute("sysUserInfoTwoShareList", sysUserInfoTwoShareList);
		return "modules/sys/sysUserInfoFormUserShare";
	}
	
	
	
	
	
}