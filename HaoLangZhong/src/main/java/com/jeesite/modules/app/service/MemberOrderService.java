package com.jeesite.modules.app.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.MemberOrderDao;
import com.jeesite.modules.app.entity.MemberOrder;
import com.jeesite.modules.app.entity.UserInfo;

@Service
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
public class MemberOrderService extends CrudService<MemberOrderDao, MemberOrder> {
	@Autowired
	private  UserInfoService userInfoService ;
	public void updateUserMemberTime(MemberOrder memberOrder) {
		// TODO Auto-generated method stub
		String userId=memberOrder.getUserid();
		UserInfo userInfo =new UserInfo();
		userInfo.setId(userId);
		userInfo=userInfoService.get(userInfo);
		userInfo.setMemberFlag("1");
		userInfo.setMemberStart(new Date());
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date); 
		calendar.add(calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动
		date=calendar.getTime();
		userInfo.setMemberEnd(date);
	}

}
