package com.xxcb.util;

public class ContentUtil {
	
	public static boolean isScoreCode(String content)
	{
		String number = "0123456789";
		int i = 0;
		while(i < content.length())
		{
			if(!number.contains(content.substring(i, i+1)))
			{
				break;
			}
			i++;
		}
		if( ( i == content.length())&&(content.length() == 6))
		{
			return true;
		}
		return false;
	}
	
	public static boolean isPhoneNumber(String content)
	{
		String number = "0123456789";
		int i = 0;
		while(i < content.length())
		{
			if(!number.contains(content.substring(i, i+1)))
			{
				break;
			}
			i++;
		}
		if( ( i == content.length())&&((content.length() == 11) ||(content.length() == 12)))
		{
			return true;
		}
		return false;
		
	}
	
	public static void main(String [] args)
	{
		System.out.println(isScoreCode("123456"));
	}
}
