package com.xxcb.util;

import java.util.Random;

/**
 * 邀请码生成工具类
 * @author lidu
 * @data 2015.10.12
 *
 */
public class InvitationCodeUtil {
	public static String getRandomCharAndNumr() {  
	    String str = "";  
	    Random random = new Random();  
	    for (int i = 0; i < 6; i++) {  
	       /* boolean b = random.nextBoolean();  
	        if (b) { // 字符串  
	            str += (char) (97 + random.nextInt(26));// 取得大写字母  
	        } else { // 数字  
	            str += String.valueOf(random.nextInt(10));  
	        }  */
	    	str += String.valueOf(random.nextInt(10));  
	    }  
	    return str;  
	}  
	public static void main(String args[])
	{
		System.out.println(getRandomCharAndNumr());
	}
}
