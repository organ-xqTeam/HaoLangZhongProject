package com.jeesite.modules.app.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.dao.UserLoginDao;
import com.jeesite.modules.app.entity.User;
import com.jeesite.modules.app.utils.DateUtil;
import com.jeesite.modules.app.utils.MD5Utils;
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
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 用户登录
	 * */
	@Transactional(readOnly=false)
	public JSONObject login(User u) {
		JSONObject result = null;
		Map<String, Object> user = userLoginDao.getUserInfo(u);
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
		// 校验验证码
		if (!u.getCode().equals("1111")) {
			throw new CodeCheckException();
		}
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
		if (!u.getCode().equals("1111")) {
			throw new CodeCheckException();
		}
		// 设置新密码
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("password", MD5Utils.stringToMD5(u.getNewpass()));
		params.put("id", user.get("id"));
		userLoginDao.updateUser(params);
		return true;
	}
	
}
