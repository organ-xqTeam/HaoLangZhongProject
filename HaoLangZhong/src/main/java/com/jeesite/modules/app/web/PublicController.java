package com.jeesite.modules.app.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.ConsultationDiscuss;
import com.jeesite.modules.app.entity.Public;
import com.jeesite.modules.app.entity.PublicDiscuss;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.service.DoctorLabelService;
import com.jeesite.modules.app.service.PublicService;
import com.jeesite.modules.app.service.UserInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
  * 公共咨询的Controller
 * @author 1111111
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/public")
public class PublicController extends BaseController {
	@Autowired
	private  PublicService  publicService;
	@Autowired
	private StringRedisTemplate redis;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private DoctorLabelService doctorLabelService;
	
	/**
	 * 展示所有免费咨询的订单
	 */
	@ResponseBody
	@RequestMapping(value = "/getListAll")
	public Result getListAll(@RequestBody Map<String, Object> requestMap,HttpServletRequest request, HttpServletResponse response) {
		try {
			Public pub =new Public();
			Integer pageNo=Integer.valueOf((String)requestMap.get("pageNo")) ;
			Integer pageSize=Integer.valueOf((String)requestMap.get("pageSize")) ;
			pub.setPage(new Page<>(pageNo, pageSize));
			Page<Public> pubList= publicService.findPage(pub);
			List<Public> publicList= pubList.getList();
			for (int i = 0; i < publicList.size(); i++) {
				String userId=publicList.get(i).getUserId();
				UserInfo userInfo =new UserInfo();
				userInfo.setId(userId);
				userInfo=userInfoService.get(userInfo);
				if(userInfo!=null&&userInfo.getIcon()!=null) {
					publicList.get(i).setUserIcon(userInfo.getIcon());
				}
			}
			long count= publicService.findCount(pub);
			JSONObject result = new JSONObject();
			result.put("items", publicList);
			result.put("count", count);
			return Result.success(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 保存免费咨询订单得操作
	 * @param requestMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrder")
	public Result saveOrder(@RequestBody Map<String, Object> requestMap) {
		try {
			/*TokenTools.checkToken(requestMap.get("token").toString(), redis);*/
			Map<String, Object> items=publicService.savePublic(requestMap);
			return Result.success(items);
		}catch (RedisCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.TOKEN_INVALID);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	
	
	/**
	 * 获取咨询信息详情
	 * */
	@ResponseBody
	@RequestMapping(value = "/findPublicById/{id}")
	public Result findPublicById(@PathVariable String id) {
		try {
			JSONObject resultJson = new JSONObject();
			//通过主键得到
			Public pub = publicService.findPublicById(id);
			String body =pub.getBody();
			String therapy =pub.getTherapy();
			String disease =pub.getDisease();
			String [] bodys= {};
			if(body!=null) { bodys= body.split(",");}
			String [] therapys = {};
			if(therapy!=null) {
				therapys=therapy.split(",");
			}
			List<String> therapyArr = new ArrayList<String>();
			List<String> bodyArr = new ArrayList<String>();
			List<String> diseaseArr=new ArrayList<String>();
			diseaseArr.add(disease);
			for(String s : bodys) {
				bodyArr.add(s);
			}
			for(String s : therapys) {
				therapyArr.add(s);
			}
			resultJson.put("consultation", pub);
			resultJson.put("pics", publicService.findPublicPic(pub.getId()));
/*			resultJson.put("body", doctorLabelService.queryListByIds(bodyArr));
			resultJson.put("therapys", doctorLabelService.queryListByIds(therapyArr));*/
			resultJson.put("disease", doctorLabelService.queryListByIds(diseaseArr));
			PublicDiscuss publicDiscuss =new PublicDiscuss();
			publicDiscuss.setPublicId(pub.getId());

			resultJson.put("publicDiscussList", publicService.findconsultationDiscussList(publicDiscuss));
			
			Object userIdObj=  pub.getUserId();
			String userId=null;
			if(userIdObj!=null) {
				userId=(String) userIdObj;
			}
			UserInfo userInfo  =new UserInfo();
			userInfo=userInfoService.get(userId);
			resultJson.put("userInfo", userInfo);
			return Result.success(resultJson);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	/**
	 * 
	 * 发送讨论信息的操作
	 */
	@ResponseBody
	@RequestMapping(value = "/sendInfo")
	public Result sendInfo(@RequestBody Map<String, Object> requestMap) {
		try {
			//TokenTools.checkToken(requestMap.get("token").toString(), redis);
			requestMap.get("token");
			String userId=(String) requestMap.get("userId");
			String inputval=(String) requestMap.get("inputval");
			/*String orderId=(String) requestMap.get("orderId");*/
			String discussState=(String) requestMap.get("discussState");
			String publicId=(String) requestMap.get("publicId");
			/*consultationOrderService.updateOrderPay(requestMap);*/
			PublicDiscuss publicDiscuss =new PublicDiscuss();
			publicDiscuss.setUserId(userId);
			publicDiscuss.setContent(inputval);
			publicDiscuss.setPublicId(publicId);
			publicDiscuss.setCreateDate(new Date());
			// 0 患者  1 医生
			publicDiscuss.setDiscussState(discussState);
			publicService.insertPublicDiscuss(publicDiscuss);
			return Result.success(true);			
		}
		catch (RedisCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.TOKEN_INVALID);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}


}
