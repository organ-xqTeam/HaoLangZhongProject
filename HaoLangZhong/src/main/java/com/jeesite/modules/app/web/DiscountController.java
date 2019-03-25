package com.jeesite.modules.app.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.entity.Discount;
import com.jeesite.modules.app.entity.DiscountDetail;
import com.jeesite.modules.app.service.DiscountDetailService;
import com.jeesite.modules.app.service.DiscountService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;


/**
 * 
 * @author 范耘诚
 * 红包Controller
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/discountController")
public class DiscountController {
	@Autowired
	private  DiscountService discountService;
	@Autowired
	private DiscountDetailService  discountDetailService;
	
	/**
	 * 	
	 ** token    token     随便
	 * userId     用户id   123
	 *   展示红包信息
	 * /js/f/sys/discountController/showDiscount
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/showDiscount" ,method=RequestMethod.POST)
	public Result showDiscount (HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		try {
			String token=(String) requestParams.get("token");
			//获取token------end
			String userId=(String)requestParams.get("userId");
			Discount discount =new Discount();
			discount.setUserId(userId);
			discount.setId(null);
			List<Discount> discountList= discountService.findList(discount);
			if(discountList==null||discountList.size()==0) {
				Map paramMap = new HashMap<>();
				paramMap.put("createDate", new Date());
				paramMap.put("userId", userId);
				discountService.insertDiscount(paramMap);
				discount.setDiscountPrice("0");
				discount.setCreateDate(new Date());
			}else {
				discount=discountList.get(0);
			}
			discount.setDiscountDetail(null);
			JSONObject result = new JSONObject();
			result.put("discount", discount);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
		
		
	}
	/**
	 * 	
	 ** token    token     随便
	 * userId     用户id   123
	 * discountId  红包的主键id 
	 *   展示红包详情信息
	 * /js/f/sys/discountController/showDiscountDetail
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/showDiscountDetail" ,method=RequestMethod.POST)
	public Result showDiscountDetail(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		//List<Discount> discountList= discountService.findList(discount);
		try {
			String token=(String) requestParams.get("token");
			//获取token------end
			String userId=(String)requestParams.get("userId");
			//获取token------end
			String discountId=(String)requestParams.get("discountId");
			DiscountDetail discountDetail =new DiscountDetail();
			discountDetail.setDiscountId(Long.valueOf(discountId));
			List<DiscountDetail> discountDetailList= discountDetailService.findList(discountDetail);
			JSONObject result = new JSONObject();
			result.put("discountDetailList", discountDetailList);
			return Result.success(result);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
		

	}

}
