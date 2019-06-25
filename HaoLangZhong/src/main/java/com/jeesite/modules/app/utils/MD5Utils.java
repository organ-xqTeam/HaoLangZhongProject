package com.jeesite.modules.app.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	
	 public static String stringToMD5(String plainText) {
	    byte[] secretBytes = null;
	    try {
	        secretBytes = MessageDigest.getInstance("md5").digest(
	                plainText.getBytes());
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("没有这个md5算法！");
	    }
	    String md5code = new BigInteger(1, secretBytes).toString(16);
	    for (int i = 0; i < 32 - md5code.length(); i++) {
	        md5code = "0" + md5code;
	    }
	    return md5code;
	}
	public final static String getMessageDigest(byte[] buffer) {
	        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	        try {
	            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	            mdTemp.update(buffer);
	            byte[] md = mdTemp.digest();
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str);
	        } catch (Exception e) {
	            return null;
	        }
	    }
	
}
