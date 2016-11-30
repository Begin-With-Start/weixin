package com.xyt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * 字符串工具类
 * 
 * @author ChenPengFei
 */
public class StringUtil {
   
	/**
	 * 判断是否为手机号码
	 * @param mobile
	 * @return
	 */
	public static boolean isMobileNO(String mobile){ 
		if(StringUtils.isEmpty(mobile)) {
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(145)|(147)|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$"); 
		Matcher m = p.matcher(mobile);
		return m.matches(); 
	}
   
}
