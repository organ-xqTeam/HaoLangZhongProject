package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.modules.app.dao.UserCollectionDao;
import com.jeesite.modules.app.entity.UserCollection;

/**
 * 用户收藏Service
 * @author 李昊翀
 * @version 2019-03-11
 */
@Service
@Transactional(readOnly=true)
public class UserCollectionService {
	
	@Autowired
	private UserCollectionDao userCollectionDao;
	
	/**
	 * 获取用户关注的医生列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryDoctorList(UserCollection u) {
		return userCollectionDao.queryDoctorList(u);
	}
	
	/**
	 * 获取用户关注的医生列表count
	 * */
	@Transactional(readOnly=false)
	public int queryDoctorCount(UserCollection u) {
		return userCollectionDao.queryDoctorCount(u);
	}
	
	/**
	 * 获取用户收藏列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryArticleList(UserCollection u) {
		return userCollectionDao.queryArticleList(u);
	}
	
	/**
	 * 获取用户收藏列表count
	 * */
	@Transactional(readOnly=false)
	public int queryArticleCount(UserCollection u) {
		return userCollectionDao.queryArticleCount(u);
	}
	
	/**
	 * 保存收藏信息
	 * */
	@Transactional(readOnly=false)
	public int saveUserCollection(Map<String, Object> params) {
		return userCollectionDao.saveUserCollection(params);
	}
	
	/**
	 * 取消收藏
	 * */
	@Transactional(readOnly=false)
	public int cancelCollection(String userid, String collectionid) {
		return userCollectionDao.cancelCollection(userid, collectionid);
	}
	
}
