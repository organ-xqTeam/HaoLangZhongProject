package com.jeesite.modules.app.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.app.utils.AlipayUtil;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.config.WeixinPayConfig;
import com.jeesite.modules.app.utils.wxapputil.MD5Util;
import com.jeesite.modules.app.utils.wxapputil.PrepayIdRequestHandler;
import com.jeesite.modules.app.utils.wxapputil.UUID;
import com.jeesite.modules.app.utils.wxapputil.WXUtil;
import com.jeesite.modules.app.utils.wxapputil.XMLUtil;
/**
 * 支付的controller
 * @author 1111111
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/payController")
public class PayController {
	private String out_trade_no = "";
	/*
	 * payController/aliAppPay
	 * 
	 * http://25368527vi.zicp.vip/js/f/sys/payController/aliAppPay
	 */
	//支付宝支付信息
	@ResponseBody
	@RequestMapping(value = "/aliAppPay")
	public Result aliAppPay(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		//第三方订单号
		String out_trade_no = String.valueOf(UUID.next());
		String orderNo=requestParams.get("orderNo").toString();
		String token=requestParams.get("token").toString();
		//价格单位元
		//String total_amount=requestParams.get("total_fee").toString();
		String total_amount="0.01";
		//商品详情
		String body=requestParams.get("body").toString();
		//商品主题
		//String subject=requestParams.get("subject").toString();
		String subject=requestParams.get("body").toString();
		String resultStr=AlipayUtil.getsign(total_amount, out_trade_no, subject, body);
		JSONObject result = new JSONObject();
		result.put("orderInfo", resultStr);
		return  Result.success(result);
	}
	//支付宝异步回调
	/*
	 * payController/aliPayNotify
	 */
	@ResponseBody
	@RequestMapping(value = "/aliPayNotify")
	public Result aliPayNotify(HttpServletRequest request,@RequestBody Map<String, Object> requestParams) {
		
		AlipayUtil.aliPay_notify(requestParams);
		return  Result.success(true);
	}
	
	 
	    
	    /**
	     * 生成预支付订单，获取prepayId
	     * @param request
	     * @param response
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "/getprepay", method = RequestMethod.POST)
	    @ResponseBody
	    public Result getOrder(HttpServletRequest request, HttpServletResponse response,@RequestBody Map<String, Object> requestParams)
	            throws Exception {
	        try {
				Map<String, Object> map = new HashMap();
				// 获取生成预支付订单的请求类
				PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
      // String totalFee = request.getParameter("total_fee");
				String totalFee = requestParams.get("total_fee").toString();
				//int total_fee=(int) (Float.valueOf(totalFee)*100);
				int total_fee=1;
				System.out.println("total:"+total_fee);
				System.out.println("total_fee:" + total_fee);
				prepayReqHandler.setParameter("appid", WeixinPayConfig.APP_ID);
				prepayReqHandler.setParameter("body", WeixinPayConfig.BODY);
				prepayReqHandler.setParameter("mch_id", WeixinPayConfig.MCH_ID);
				String nonce_str = WXUtil.getNonceStr();
				prepayReqHandler.setParameter("nonce_str", nonce_str);
				prepayReqHandler.setParameter("notify_url", WeixinPayConfig.NOTIFY_URL);
				out_trade_no = String.valueOf(UUID.next());
				prepayReqHandler.setParameter("out_trade_no", out_trade_no);
				prepayReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());
				String timestamp = WXUtil.getTimeStamp();
				prepayReqHandler.setParameter("time_start", timestamp);
				System.out.println(String.valueOf(total_fee));
				prepayReqHandler.setParameter("total_fee", String.valueOf(total_fee));
				prepayReqHandler.setParameter("trade_type", "APP");
				/**
				 * 注意签名（sign）的生成方式，具体见官方文档（传参都要参与生成签名，且参数名按照字典序排序，最后接上APP_KEY,转化成大写）
				 */
				prepayReqHandler.setParameter("sign", prepayReqHandler.createMD5Sign());
				prepayReqHandler.setGateUrl(WeixinPayConfig.GATEURL);
				String prepayid = prepayReqHandler.sendPrepay();
				/*prepayid=prepayid.substring(2);*/
				// 若获取prepayid成功，将相关信息返回客户端
				if (prepayid != null && !prepayid.equals("")) {
				   //预付款
					String signs = "appid=" + WeixinPayConfig.APP_ID + "&noncestr=" + nonce_str + "&package=Sign=WXPay&partnerid="
				            + WeixinPayConfig.PARTNER_ID + "&prepayid=" + prepayid + "&timestamp=" + timestamp + "&key="
				            + WeixinPayConfig.APP_KEY;
				    map.put("code", 0);
				    map.put("info", "success");
				    /**
				     * 签名方式与上面类似
				     */
				    map.put("appid", WeixinPayConfig.APP_ID);
				    map.put("noncestr", nonce_str);   //与请求prepayId时值一致
				    map.put("package", "Sign=WXPay");  //固定常量
				    map.put("partnerid", WeixinPayConfig.PARTNER_ID);
				    map.put("prepayid", prepayid);
				    map.put("timestamp", timestamp);  //等于请求prepayId时的time_start
				    System.out.println("高建磊给的方法生成的sign:"+getSign(map));
				    System.out.println("原来得生成得sign:"+MD5Util.MD5Encode(signs, "utf8").toUpperCase());
				    map.put("sign", getSign(map)/*MD5Util.MD5Encode(signs, "utf8").toUpperCase()*/);
				} else {
				    map.put("code", 1);
				    map.put("info", "获取prepayid失败");
				}
				
				return Result.success(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Result.error(CodeMsg.PARAMETER_ISNULL);
			}
	        
	    }
	    public static String getSign(Map<String, Object> req) {
	        Map<String, String> map = new HashMap<>();
	        map.put("appid", req.get("appid").toString());
	        map.put("partnerid", req.get("partnerid").toString());
	        map.put("prepayid", req.get("prepayid").toString());
	        map.put("package", req.get("package").toString());
	        map.put("noncestr", req.get("noncestr").toString());
	        map.put("timestamp", req.get("timestamp").toString());

	        ArrayList<String> sortList = new ArrayList<>();
	        sortList.add("appid");
	        sortList.add("partnerid");
	        sortList.add("prepayid");
	        sortList.add("package");
	        sortList.add("noncestr");
	        sortList.add("timestamp");
	        sort(sortList);

	        String md5 = "";
	        int size = sortList.size();
	        for (int k = 0; k < size; k++) {
	            if (k == 0) {
	                md5 += sortList.get(k) + "=" + map.get(sortList.get(k));
	            } else {
	                md5 += "&" + sortList.get(k) + "=" + map.get(sortList.get(k));
	            }
	        }
	        String stringSignTemp = md5 + "&key=aeQTWtzckzj4deFDjdVC44NXdPxpO09L";

	        String sign = Md5(stringSignTemp).toUpperCase();

	        return sign;
	    }
	    private static void sort(ArrayList<String> strings) {
	        Collections.sort(strings);
	    }
	    public static final String Md5(String s) {
	        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	        try {
	            byte[] e = s.getBytes("UTF-8");
	            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	            mdTemp.update(e);
	            byte[] md = mdTemp.digest();
	            int j = md.length;
	            char[] str = new char[j * 2];
	            int k = 0;

	            for (int i = 0; i < j; ++i) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 15];
	                str[k++] = hexDigits[byte0 & 15];
	            }

	            return new String(str);
	        } catch (Exception var10) {
	            return null;
	        }
	    }

	    /**
	     * 接收微信支付成功通知
	     * @param request
	     * @param response
	     * @throws IOException
	     * @throws org.jdom2.JDOMException 
	     */
	    @RequestMapping(value = "/wxnotify")
	    public void getnotify(HttpServletRequest request, HttpServletResponse response)
	            throws IOException, org.jdom2.JDOMException {
	        System.out.println("微信支付回调");
	        PrintWriter writer = response.getWriter();
	        InputStream inStream = request.getInputStream();
	        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        while ((len = inStream.read(buffer)) != -1) {
	            outSteam.write(buffer, 0, len);
	        }
	        outSteam.close();
	        inStream.close();
	        String result = new String(outSteam.toByteArray(), "utf-8");
	        System.out.println("微信支付通知结果：" + result);
	        Map<String, String> map = null;
	        /**
			 * 解析微信通知返回的信息
			 */
			map = XMLUtil.doXMLParse(result);
	        System.out.println("=========:"+result);
	        // 若支付成功，则告知微信服务器收到通知
	        if (map.get("return_code").equals("SUCCESS")) {
	            if (map.get("result_code").equals("SUCCESS")) {
	                //对数据库操作
	            }
	        }
	    }

}
