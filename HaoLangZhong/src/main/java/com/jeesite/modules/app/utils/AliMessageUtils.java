package com.jeesite.modules.app.utils;

import org.junit.Test;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;



public class AliMessageUtils {
	/**
     * 发送短信消息方法,返回验证码
     *
     * @param phone
     * @return
     */
    public static String sendMsg(String phone) throws Exception {
 
    	 DefaultProfile profile = DefaultProfile.getProfile("default", "LTAImMiKSwnQOclt", "I96GDEQirqoWocypy5CQEUU2u3wg8y");
         IAcsClient client = new DefaultAcsClient(profile);

         CommonRequest request = new CommonRequest();
         //request.setProtocol(ProtocolType.HTTPS);
         request.setMethod(MethodType.POST);
         request.setDomain("dysmsapi.aliyuncs.com");
         request.setVersion("2017-05-25");
         request.setAction("SendSms");
         request.putQueryParameter("PhoneNumbers", phone);
         request.putQueryParameter("SignName", "百科郎中");
         request.putQueryParameter("TemplateCode", "SMS_167325151");
         String checkCode = CoreUtils.randomString(6, true);//此处是生成6位数验证码工具类
         request.putQueryParameter("TemplateParam", "{\"code\":\""+checkCode+"\"}");
         try {
             CommonResponse response = client.getCommonResponse(request);
             System.out.println(response.getData());
         } catch (ServerException e) {
             e.printStackTrace();
         } catch (ClientException e) {
             e.printStackTrace();
         }
		return checkCode;
     }
    
    //测试发送短信
    @Test
    public void test1() throws Exception {
        String s = this.sendMsg("13840425653");//手机号
        System.out.println(s);
    }
}
