package com.jeesite.modules.app.utils;

import java.util.HashSet;
import java.util.Set;

public class PictureJudgment {
	
	public static boolean imagesC(String prefix){
		
		if ("".equals(prefix)) {
			return false;
		}
		
		Set<String> set = new HashSet<String>();
		set.add("jpg");
		set.add("png");
		set.add("tif");
		set.add("gif");
		set.add("pcx");
		set.add("tga");
		set.add("exif");
		set.add("fpx");
		set.add("svg");
		set.add("psd");
		set.add("cdr");
		set.add("pcd");
		set.add("dxf");
		set.add("ufo");
		set.add("eps");
		set.add("ai");
		set.add("raw");
		set.add("WMF");
		set.add("webp");
		set.add("bmp");
		if (set.contains(prefix)) {
			return true;
		}
		return false;
	}
	
}
