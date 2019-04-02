package com.jeesite.modules.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.AirDrug;
import com.jeesite.modules.app.entity.UserInfo;

@MyBatisDao
public interface UserInfoDao extends CrudDao<UserInfo> {

	int saveUserInfo(Map<String, Object> params);
	
	Map<String, Object> findUserInfo(String id);
	Map<String, Object> findDoctorInfo(String id);
	
	List<Map<String, Object>> findDoctorPic(String id);
	
	int saveDoctorInfo(Map<String, Object> params);
	
	int saveDoctorPic(Map<String, Object> params);
	
	int updateDoctorInfo(Map<String, Object> params);
	
	int deleteDoctorLable(@Param("doctorid") String doctorid);
	
	int saveDoctorLable(List<Map<String, Object>> params);
	
	int updateDoctorPic(Map<String, Object> params);
	/**通过电话的到userInfo信息*/
	Map getByMobile(Map pamMap);
	/**通过邀请码的到用户信息*/
	Map getByshareCode(String shareCode);
	/**通过电话修改已经填写过邀请码了*/
	void updateIsInviteByMobile(Map pamMap);
	
	
}