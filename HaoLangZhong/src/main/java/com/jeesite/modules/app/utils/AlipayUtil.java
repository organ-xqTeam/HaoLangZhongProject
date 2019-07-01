package com.jeesite.modules.app.utils;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
 
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.jeesite.modules.app.utils.config.AliAppPayConfig;

public class AlipayUtil {
	
	
	
	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AlipayUtil.class);
	
	/**
	 * 创建商户订单  请求支付宝
	 * @param amount
	 * @param random
	 * @return
	 */
	public static String getsign(String amount,String OutTradeNo,String subject,String body){
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(AliAppPayConfig.GATE, 
				AliAppPayConfig.APPID,
				AliAppPayConfig.APP_PRIVATE_KEY, 
				"json", AliAppPayConfig.CHARSET, 
				AliAppPayConfig.ALIPAY_PUBLIC_KEY, 
				"RSA2");
		 if (Double.valueOf(amount) <= 0){ // 一些必要的验证，防止抓包恶意修改支付金额
			 return null;
	     }
		 //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(OutTradeNo);  //订单号
        model.setTimeoutExpress("30m");  // 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        model.setTotalAmount(amount); // 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]这里调试每次支付1分钱，在项目上线前应将此处改为订单的总金额
        model.setProductCode("QUICK_MSECURITY_PAY");// 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        request.setBizModel(model);
        // 设置后台异步通知的地址，在手机端支付成功后支付宝会通知后台，手机端的真实支付结果依赖于此地址
        request.setNotifyUrl("http://25368527vi.zicp.vip/js/f/sys/payController/aliPayNotify");
        AlipayTradeAppPayResponse response = new AlipayTradeAppPayResponse();
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.getBody();
	}
	
	/**
	 * 支付宝支付成功后.异步请求该接口
	 * @param requestParams
	 * @return
	 */
	public static Map<String,String> aliPay_notify(Map requestParams){
        System.out.println("支付宝支付结果通知"+requestParams.toString());
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
 
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[])requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
            }
          //乱码解决，这段代码在出现乱码时使用。
          //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
          params.put(name, valueStr);
         }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        try {
            boolean flag = AlipaySignature.rsaCheckV1(params, AliAppPayConfig.PUBLIC_KEY, AliAppPayConfig.CHARSET, "RSA2");
            if(flag){
                if("TRADE_SUCCESS".equals(params.get("trade_status"))){
                    //付款金额
                    String amount = params.get("buyer_pay_amount");
                    //商户订单号
                    String out_trade_no = params.get("out_trade_no");
                    //支付宝交易号
                    String trade_no = params.get("trade_no");
                    //附加数据
                   /* String passback_params = URLDecoder.decode(params.get("passback_params"));*/
                    
                    LOGGER.debug("将要存入数据库的参数"+ amount+","+out_trade_no+","+trade_no+",");
                }
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
      return params;
    }
	
	/** 
	  * 从request中获得参数Map，并返回可读的Map 
	  *  
	  * @param request 
	  * @return 
	  */  
	 @SuppressWarnings("unchecked")  
	 public static Map getParameterMap(HttpServletRequest request) {  
	     // 参数Map  
	     Map properties = request.getParameterMap();  
	     LOGGER.debug("包装参数："+properties);
	     // 返回值Map  
	     Map<String, String> returnMap = new HashMap<String, String>();  
	     Iterator entries = properties.entrySet().iterator();  
	     Map.Entry entry;  
	     String name = "";  
	     String value = "";  
	     while (entries.hasNext()) {  
	         entry = (Map.Entry) entries.next();  
	         name = (String) entry.getKey();  
	         Object valueObj = entry.getValue();  
	         if(null == valueObj){  
	             value = "";  
	         }else if(valueObj instanceof String[]){  
	             String[] values = (String[])valueObj;  
	             for(int i=0;i<values.length;i++){  
	                 value = values[i] + ",";  
	             }  
	             value = value.substring(0, value.length()-1);  
	         }else{  
	             value = valueObj.toString();  
	         }  
	         returnMap.put(name, value);  
	     }  
	     return returnMap;  
	 }    

}
