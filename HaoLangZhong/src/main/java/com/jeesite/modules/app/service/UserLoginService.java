package com.jeesite.modules.app.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.dao.DoctorPicDao;
import com.jeesite.modules.app.dao.UserLoginDao;
import com.jeesite.modules.app.entity.DoctorInfo;
import com.jeesite.modules.app.entity.DoctorPic;
import com.jeesite.modules.app.entity.Share;
import com.jeesite.modules.app.entity.User;
import com.jeesite.modules.app.entity.UserInfo;
import com.jeesite.modules.app.entity.UserShare;
import com.jeesite.modules.app.utils.DateUtil;
import com.jeesite.modules.app.utils.MD5Utils;
import com.jeesite.modules.app.utils.RC4Util;
import com.jeesite.modules.app.utils.TokenProccessor;
import com.jeesite.modules.app.utils.exception.CodeCheckException;
import com.jeesite.modules.app.utils.exception.MobileRepeatException;
import com.jeesite.modules.app.utils.exception.NotExistException;
import com.jeesite.modules.app.utils.exception.NotLoginException;
import com.jeesite.modules.app.utils.exception.PassCheckException;
import com.jeesite.modules.app.utils.exception.WrongPassException;

/**
 * 用户登录注册
 * @author 李昊翀
 * @version 2019-03-05
 */
@Service
@Transactional(readOnly=true)
public class UserLoginService {

	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private  UserInfoService  userInfoService;
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private DoctorPicDao  doctorPicDao;
	@Autowired
	private ShareService shareService;
	@Autowired
	private UserShareService userShareService;
	/**
	 * 用户登录
	 * */
	@Transactional(readOnly=false)
	public JSONObject login(User u) {
		Map<String, Object> user = userLoginDao.getUserInfo(u);
		JSONObject result = null;
		Map<String, Object> params = new HashMap<String, Object>();
		// 校验用户是否存在
		if (user == null) {
			throw new NotExistException();
		}
		// 校验密码是否正确
		if (!user.get("password").equals(MD5Utils.stringToMD5(u.getPassword()))) {
			throw new WrongPassException();
		}
		// 设置最后登录时间
		params.put("id", user.get("id"));
		params.put("lastlogin", DateUtil.getSysTime1());
		userLoginDao.updateUser(params);
		// 设置用户token
		user.put("token", TokenProccessor.makeToken());
		stringRedisTemplate.opsForValue().set(user.get("token").toString(), user.toString());
		result = new JSONObject(user);
		
		//判断是否为医生
		UserInfo userInfo =new UserInfo();
		userInfo.setId((String)user.get("id"));
		userInfo=userInfoService.get(userInfo);
		String type= userInfo.getType();
		if(type.equals("2")) {
			String isauthentication = userInfo.getIsauthentication();
			Map<String, Object> docMap = new  HashMap<>();
			docMap.put("doctorid",userInfo.getId());
			Map<String, Object> map= userLoginDao.getDoctorBydoctorid(docMap);
			if(map!=null&&map.containsKey("id")&&!isauthentication.trim().equals("0")) {
				result.put("isauth", true);
				return result;
			}
			if(isauthentication!=null&&!isauthentication.trim().equals("0")) {
				result.put("isauth", false);
				return result;
			}
			result.put("doctorInfo", map);
			//查找医生图片
			DoctorPic doctorPic =new DoctorPic();
			doctorPic.setDoctorid((String)user.get("id"));
			List<DoctorPic>  doctorPicList=doctorPicDao.findList(doctorPic);
			if(doctorPicList!=null&&doctorPicList.size()>0) {
				result.put("doctorPic", doctorPicList.get(0));
			}
		}
		//分享的信息
		String shareCode="";
		Share share =new Share();
		share.setId("1");
		share=shareService.get(share);
		
		UserShare userShare =new UserShare();
		userShare.setUserId(userInfo.getId());
		List<UserShare> userShareList= userShareService.findList(userShare);
		if(userShareList.size()==0) {
			Map<String, Object> paramMap =new HashMap<>();
			paramMap.put("userId", userInfo.getId());
			shareCode =RC4Util.getShareCode(userInfo.getId());
			paramMap.put("userId", userInfo.getId());
			paramMap.put("shareCode", shareCode);
			paramMap.put("createDate", new Date());
			//插入新的分享数据
			userShareService.insertUserShareMap(paramMap);
		}else {
			userShare=userShareList.get(0);
			shareCode=userShare.getShareCode();
		}
		share.setShareCode(shareCode);
		result.put("share", share);
   		return result;
	}
	
	/**
	 * 第三方用户登录
	 * */
	@Transactional(readOnly=false)
	public JSONObject thirdLogin(User u) {
		JSONObject result = null;
		Map<String, Object> user = userLoginDao.getUserInfo(u);
		Map<String, Object> params = new HashMap<String, Object>();
		// 校验用户是否存在
		if (user == null) {
			throw new NotExistException();
		}
		//第三方登录去除校验密码
		/*// 校验密码是否正确
		if (!user.get("password").equals(MD5Utils.stringToMD5(u.getPassword()))) {
			throw new WrongPassException();
		}*/
		// 设置最后登录时间
		params.put("id", user.get("id"));
		params.put("lastlogin", DateUtil.getSysTime1());
		userLoginDao.updateUser(params);
		// 设置用户token
		user.put("token", TokenProccessor.makeToken());
		stringRedisTemplate.opsForValue().set(user.get("token").toString(), user.toString());
		result = new JSONObject(user);
		
		//判断是否为医生
		UserInfo userInfo =new UserInfo();
		userInfo.setId((String)user.get("id"));
		userInfo=userInfoService.get(userInfo);
		String type= userInfo.getType();
		if(type.equals("2")) {
			String isauthentication = userInfo.getIsauthentication();
			Map<String, Object> docMap = new  HashMap<>();
			docMap.put("doctorid",userInfo.getId());
			Map<String, Object> map= userLoginDao.getDoctorBydoctorid(docMap);
			if(map!=null&&map.containsKey("id")&&!isauthentication.trim().equals("0")) {
				result.put("isauth", true);
				return result;
			}
			if(isauthentication!=null&&!isauthentication.trim().equals("0")) {
				result.put("isauth", false);
				return result;
			}
			result.put("doctorInfo", map);
			//查找医生图片
			DoctorPic doctorPic =new DoctorPic();
			doctorPic.setDoctorid((String)user.get("id"));
			List<DoctorPic>  doctorPicList=doctorPicDao.findList(doctorPic);
			if(doctorPicList!=null&&doctorPicList.size()>0) {
				result.put("doctorPic", doctorPicList.get(0));
			}
		}
		//分享的信息
		String shareCode="";
		Share share =new Share();
		share.setId("1");
		share=shareService.get(share);
		
		UserShare userShare =new UserShare();
		userShare.setUserId(userInfo.getId());
		List<UserShare> userShareList= userShareService.findList(userShare);
		if(userShareList.size()==0) {
			Map<String, Object> paramMap =new HashMap<>();
			paramMap.put("userId", userInfo.getId());
			shareCode =RC4Util.getShareCode(userInfo.getId());
			paramMap.put("userId", userInfo.getId());
			paramMap.put("shareCode", shareCode);
			paramMap.put("createDate", new Date());
			//插入新的分享数据
			userShareService.insertUserShareMap(paramMap);
		}else {
			userShare=userShareList.get(0);
			shareCode=userShare.getShareCode();
		}
		share.setShareCode(shareCode);
		result.put("share", share);
		
		
		return result;
	}
	
	/**
	 * 退出登录
	 * */
	@Transactional(readOnly=false)
	public Object cancel(String token) {
		String value = stringRedisTemplate.opsForValue().get(token);
		// 校验redis里是否有token
		if (value == null) {
			throw new NotLoginException();
		}
		stringRedisTemplate.delete(token);
		return true;
	}
	
	/**
	 * 用户注册
	 * */
	@Transactional(readOnly=false)
	public JSONObject register(User u) {
		JSONObject result = null;
		String time = DateUtil.getSysTime1();
		// 校验密码重复
		if (!u.getPassword().equals(u.getConfirm())) {
			throw new PassCheckException();
		}
		/*// 校验验证码
		if (!u.getCode().equals("1111")) {
			throw new CodeCheckException();
		}*/
		// 校验手机是否被注册过
		if (userLoginDao.registerCheck(u.getMobile()) != 0) {
			throw new MobileRepeatException();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", u.getMobile());
		params.put("password", MD5Utils.stringToMD5(u.getPassword()));
		params.put("token", TokenProccessor.makeToken());
		params.put("regtime", time);
		params.put("islock", "1");
		if (u.getType().equals("1")) {		// 普通用户
			params.put("type", "1");
			params.put("isauthentication", "0");
		}
		else {		//医生
			params.put("type", "2");
			params.put("isauthentication", "1");
		}
		params.put("create_date", time);
		params.put("del_flag", "0");
		userLoginDao.register(params);
		stringRedisTemplate.opsForValue().set(params.get("token").toString(), params.toString());
		result = new JSONObject(params);
		return result;
	}
	/**
	 * 第三方注册
	 * @param requestParams 
	 * */
	@Transactional(readOnly=false)
	public JSONObject thirdRegister(User u, Map<String, Object> requestParams) {
		JSONObject result = null;
		String time = DateUtil.getSysTime1();
		/*// 校验密码重复
		if (!u.getPassword().equals(u.getConfirm())) {
			throw new PassCheckException();
		}
		// 校验验证码

		if (!u.getCode().equals("1111")) {
			throw new CodeCheckException();
		}*/
		// 校验手机是否被注册过
		if (userLoginDao.registerCheck(u.getMobile()) != 0) {
			throw new MobileRepeatException();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", u.getMobile());
		/*params.put("password", MD5Utils.stringToMD5(u.getPassword()));*/
		params.put("token", TokenProccessor.makeToken());
		params.put("regtime", time);
		params.put("islock", "1");
		if (u.getType().equals("1")) {		// 普通用户
			params.put("type", "1");
			params.put("isauthentication", "0");
		}
		else {		//医生
			params.put("type", "2");
			params.put("isauthentication", "1");
		}
		params.put("create_date", time);
		params.put("del_flag", "0");
		String thirdType = requestParams.get("third_type").toString();
		String thirdId = requestParams.get("third_id").toString();
		UserInfo userInfo =new UserInfo();
		userInfo.setMobile(u.getMobile());
		userInfo.setIslock("1");
		userInfo.setType(u.getType());
		userInfo.setRegtime(time);
		userInfo.setCreateDate(new Date());
		userInfo.setDelFlag("0");
		userInfo.setThirdType(thirdType);
		userInfo.setThirdId(thirdId);
		//注册
		userInfoService.insert(userInfo);
		//userLoginDao.register(params);
		stringRedisTemplate.opsForValue().set(params.get("token").toString(), params.toString());
		result = new JSONObject(params);
		return result;
	}
	
	/**
	 * 修改密码
	 * */
	@Transactional(readOnly=false)
	public Object changePassword(User u) {
		Map<String, Object> user = userLoginDao.getUserInfo(u);
		// 校验账号是否存在
		if (user == null) {
			throw new NotExistException();
		}
		// 校验原密码是否正确
		if (!user.get("password").equals(MD5Utils.stringToMD5(u.getPassword()))) {
			throw new WrongPassException();
		}
		// 校验确认密码是否正确
		if (!u.getNewpass().equals(u.getConfirm())) {
			throw new PassCheckException();
		}
		// 设置新密码
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("password", MD5Utils.stringToMD5(u.getNewpass()));
		params.put("id", user.get("id"));
		userLoginDao.updateUser(params);
		return true;
	}
	
	/**
	 * 找回密码
	 * */
	@Transactional(readOnly=false)
	public Object forgetPassword(User u) {
		Map<String, Object> user = userLoginDao.getUserInfo(u);
		// 校验账号是否存在
		if (user == null) {
			throw new NotExistException();
		}
		// 校验确认密码是否正确
		if (!u.getNewpass().equals(u.getConfirm())) {
			throw new PassCheckException();
		}
		// 校验验证码是否正确
		/*if (!u.getCode().equals("1111")) {
			throw new CodeCheckException();
		}*/
		// 设置新密码
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("password", MD5Utils.stringToMD5(u.getNewpass()));
		params.put("id", user.get("id"));
		userLoginDao.updateUser(params);
		return true;
	}
	
}
