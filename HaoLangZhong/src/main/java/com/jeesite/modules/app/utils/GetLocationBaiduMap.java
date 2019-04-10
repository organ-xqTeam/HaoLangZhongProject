package com.jeesite.modules.app.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GetLocationBaiduMap {
	//得到城市名称
	//35.658651,139.74541       //纬度, 经度
    public static String getCity(String latitude,String longitude) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString ="http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location="+latitude+","+longitude+"&output=json&pois=1&latest_admin=1&ak=QkmLyG0KbMV85Bb9YG3VvqYexKxvwYtK";
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
    	System.out.println(result);
    	result=result.substring(result.indexOf("(")+1, result.lastIndexOf(")"));
    	System.out.println(result);
    	  Map maps = (Map)JSON.parse(result);  
    	System.out.println(maps);
    	JSONObject obj = (JSONObject) maps.get("result");
    	String city=(String) ((JSONObject)obj.get("addressComponent")).get("city");
    	return city;
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public static void main(String[] args) {
        /*String lat="31.931349213122";
        String lng="120.96189745647";

    //  System.out.println(System.getProperty("file.encoding"));  

        try {
            String s=getLocationByBaiduMap(lng, lat);
            System.out.println(s);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    	String city=getCity("35.658651","139.74541");

    	System.out.println(city);
    	

    }




}
