package com.jeesite.modules.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import sun.misc.BASE64Encoder;
 
/**
 * 生成Token的工具类
 * @author zhous
 * @since 2018-2-23 13:59:27
 *
 */
@SuppressWarnings({ "restriction" })
public class TokenProccessor {
	
	private static final TokenProccessor instance = new TokenProccessor();
	
	private TokenProccessor() {};
	public static TokenProccessor getInstance() {
		return instance;
	}
 
	/**
	 * 生成Token
	 * @return
	 */
	public static String makeToken() {
//		String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
//		try {
//			MessageDigest md = MessageDigest.getInstance("md5");
//			byte md5[] =  md.digest(token.getBytes());
//			BASE64Encoder encoder = new BASE64Encoder();
//			return encoder.encode(md5);
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
