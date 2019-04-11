/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.AirDrug;

/**
 * sys_air_drugDAO接口
 * @author 范耘诚
 * @version 2019-03-05
 */
@MyBatisDao
public interface AirDrugDao extends CrudDao<AirDrug> {
	/**热销药品优先显示5条*/
	List<Map> getAirDrugFirstList(AirDrug airDrug);
	/**通过搜索内容进行各种关键词模糊查询药品*/
	List<Map> queryListLike(Map<String, Object> paramMap);
	/**通过id获得商品详情信息*/
	Map findOneAirDrugAndInventoryById(AirDrug airDrug);
	/**通过药品id获取此药品的类别显示出相关推荐的药品  drugId*/
	List<Map> findCommendAirDrugListBydrugId(Map<String, Object> commendDrugParmMap);
	/**搜索中医药模糊搜索和选择类别之后分页*/
	List<Map<String,String>> findAirDrugSearch(Map<String, Object> airDrugSearchParmMap);
	/**通过drudIds们找到药品列表*/
	List<Map> getlistByDrudIds(List drudIdsList);
	
}