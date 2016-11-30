package com.xyt.util;

import java.util.Random;

public class XytScoreCodeCreateUtil {
	
	public static String createScoreCode()
	{
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < 8; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();  
	}
}
