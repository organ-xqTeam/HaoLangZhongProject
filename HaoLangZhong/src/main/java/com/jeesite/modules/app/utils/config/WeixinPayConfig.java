package com.jeesite.modules.app.utils.config;

public class WeixinPayConfig {
	  /**
     * 微信开发平台应用ID必须
     */
    public static final String APP_ID="wx4cac222a8fceba72";
    
    /**
     * 应用对应的密钥必须
     */
    public static final String APP_KEY="aeQTWtzckzj4deFDjdVC44NXdPxpO09L";
    /**
     * 应用对应的凭证必须
     */
    /*public static final String APP_SECRET="081e2c78f5e06cd304514491e71f50ae";*/
    /**
     * 微信支付商户号 必须
     */
    public static final String MCH_ID="1538481861";
    /**
     * 商户id 必须
     */									
    public static final String PARTNER_ID="1538481861";
    
    /**
     * 获取预支付id的接口url
     */
    public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信服务器回调通知url
     */
    //public static String NOTIFY_URL="http://25368527vi.zicp.vip/js/f/sys/payController/wxnotify";
    public static String NOTIFY_URL="http://120.92.10.2:81/hlz/f/sys/payController/wxnotify";
    
   
    
    
    
    
    
    /**
     * 商品描述
     */
    public static final String BODY="QQ游戏-账户充值";

    /**
     * 商户号对应的密钥
     */
    /*public static final String PARTNER_key="123466";*/

    
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE="client_credential";

}
