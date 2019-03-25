/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.AirDrugComment;

/**
 * sys_air_drug_commentDAO接口
 * @author 范耘诚
 * @version 2019-03-05
 */
@MyBatisDao
public interface AirDrugCommentDao extends CrudDao<AirDrugComment> {
	/**通过drugId和userId得到评价信息*/
	List<Map> findAirDrugCommentListByGrugIdAndUserId(Map<String, Object> airDrugCommentParmMap);
	/**通过 GrugId和UserId获取每个星级的数量 star_grade 星级 ,count数量*/
	List<Map> findAirDrugCommentStarCountByGrugIdAndUserId(Map<String, Object> airDrugCommentParmMap);

}