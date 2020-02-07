package com.jeesite.modules.app.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.dao.ArticleInfoDao;
import com.jeesite.modules.app.entity.ArticleInfo;

/**
 * 文章管理Service
 * @author 李昊翀
 * @version 2019-03-01
 */
@Service
@Transactional(readOnly=true)
public class ArticleInfoService {
	
	@Autowired
	private ArticleInfoDao articleInfoDao;
	
	/**
	 * 查询文章列表
	 * */
	@Transactional(readOnly=false)
	public List<Map<String, Object>> queryList(ArticleInfo requestParams) {
		return articleInfoDao.queryList(requestParams);
	}
	
	/**
	 * 查询文章列表count
	 * */
	@Transactional(readOnly=false)
	public int queryCount(ArticleInfo requestParams) {
		return articleInfoDao.queryCount(requestParams);
	}
	
	/**
	 * 查询文章详情
	 * */
	@Transactional(readOnly=false)
	public JSONObject findOneById(String id) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("articleInfo", articleInfoDao.findOneById(id));
		return resultJson;
	}

}
