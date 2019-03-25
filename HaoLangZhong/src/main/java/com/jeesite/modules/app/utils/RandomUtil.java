package com.jeesite.modules.app.utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by fan on 2019/3/13.
 */
public class RandomUtil {
    public static String getRandomCode() {
        Random rm = new Random();
        int randomInt;
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuffer randomStr = new StringBuffer();
        int strLength = str.length();
        //13位
        for (int i = 0; i < 13; i++) {
            randomInt = rm.nextInt(strLength);
            randomStr.append(str.charAt(randomInt));
        }
        return randomStr.toString();
    } // 获取6位随机数字+字母码
    
    public static String getRandomID() {
        Random rm = new Random();
        int randomInt;
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuffer randomStr = new StringBuffer();
        int strLength = str.length();
        //13位
        for (int i = 0; i < 6; i++) {
            randomInt = rm.nextInt(strLength);
            randomStr.append(str.charAt(randomInt));
        }
        return randomStr.toString();
    } // 获取6位随机数字+字母码
    
    
    private static long seqOfOrderCode = 0;//订单编号的流水号部分；
    private static String dateOfOrderCode = "";//订单编号的日期部分；
    private static String dateOfOrderCodeSSS = "";//订单编号的日期部分；
    private static NumberFormat nfo = NumberFormat.getInstance();
    private static NumberFormat num_format = NumberFormat.getInstance();
    private static SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat yyyymmdd=new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat yyyymm=new SimpleDateFormat("yyyyMM");
    private static NumberFormat nf = NumberFormat.getInstance();
    private static SimpleDateFormat dm=new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat yyyymmdd8=new SimpleDateFormat("yyyyMMdd");
    static
    {
    	nfo.setMinimumIntegerDigits(6);
    	nfo.setGroupingUsed(false);
    	num_format.setMinimumIntegerDigits(2);
    	num_format.setGroupingUsed(false);
		Date date = new Date();
		dateOfOrderCode = yyyymmdd8.format(date);
		dateOfOrderCodeSSS = dm.format(date);
    }
    
	/**
	 * 功能：获取订单编号； 充值订单 8位日期+6位流水号 20120731000001每日流水号从1开始。
	 * 说明：每日重启系统后需要从数据库查询当日最大订单编号，提取“流水号”部分，
	 *      调整这里的seqOfOrderCode的初值；
	 *      dateOfOrderCode的初值=当日8位日期；
	 * 2019/3/13 by fan
	 */
	public static synchronized String getOrderCode()
	{
		Date date = new Date();
		String orderId = dm.format(date).substring(2);
		if(0!=dateOfOrderCodeSSS.substring(2).compareTo(orderId))
		{//切换日期时，流水号复零
			seqOfOrderCode = 0;
			dateOfOrderCodeSSS = orderId;
		}

		//同日，流水号递增
		if(99==seqOfOrderCode){
			//当日流水号达到最大值时，提示流水号已满==============
			seqOfOrderCode = 0;
		}else {
			seqOfOrderCode++;
		}
		/* PrintWriter pw = null;
 	    try {
 	      File file = new File("D:\\1.txt");
 	      pw = new PrintWriter(file);
 	      pw.println(dateOfOrderCodeSSS.toString().substring(2));
 	      pw.println(num_format.format( seqOfOrderCode ).toString());
 	      pw.println(dateOfOrderCodeSSS.toString()+num_format.format( seqOfOrderCode ).toString());
 	   
 	    } catch (Exception e) {
 	      e.printStackTrace();
 	    }
 	    pw.close();*/
		String str=dateOfOrderCodeSSS.toString().substring(2);
		String strs=num_format.format( seqOfOrderCode ).toString();
		
		return str+strs;
	}
/*	public static void main(String[] args) {
		System.out.println(RandomUtil.getOrderCode());
	}*/
}
