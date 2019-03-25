package com.jeesite.modules.app.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String getSysTime1() {
		//获取系统时间：年月日时分秒
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return time.format(nowTime);
	}
	
	public static String getSysTime2() {
		//获取系统时间：年月日
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
		return time.format(nowTime);
	}
	
	/**
	 * 处理时间戳
	 * */
	public static String setTimestamp(Timestamp date) {
		String str = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = sdf.format(date);
		return str;
	}
	
}
