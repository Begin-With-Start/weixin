package com.xxcb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 猜数字游戏工具类
 * @author lidu
 * @date 2015.8.14
 *
 */
public class GameUtil {
	/**
	 * 验证是不是4位纯数字
	 */
	public static boolean verifyNumber(String number){
		boolean result = false;
		Pattern pattern = Pattern.compile("[0-9]{4}");
		Matcher matcher = pattern.matcher(number);
		//匹配成功就表名是纯数字
		if(matcher.matches())
		{
			result = true;
		}
		return result;
	}
	
	/**
	 * 验证是否有重复字符
	 * @author lidu
	 */
	public static boolean verifyRepeat(String number){
		boolean result = false;
		for(int i = 1; i < number.length(); i++)
		{
			for(int j = 0; j < i ; j++)
			{
				if(number.charAt(i) == number.charAt(j))
				{
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 生成不重复的4位随机数
	 */
	public static String generateRandNumber(){
		StringBuffer randBuffer = new StringBuffer();
		String scopeStr = "0123456789";
		Random random = new Random();
		for( int i = 0; i<4; i ++)
		{
			int num = random.nextInt(scopeStr.length());
			randBuffer.append(scopeStr.charAt(num));
			scopeStr = scopeStr.replace(String.valueOf(scopeStr.charAt(num)), "");
		}
		return randBuffer.toString();
	}
	
	
	/**
	 * 计算猜测结果
	 * @return xAyB
	 */
	public static String guessResult(String answer, String number)
	{
		int rightA = 0 ;
		int rightB = 0 ;
		//计算A的个数
		for( int i = 0; i < 4; i ++)
		{
			if(number.charAt(i) == answer.charAt(i))
			{
				rightA++;
			}
		}
		
		//计算B的个数
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0; j <4 ; j++)
			{
				if ( i != j)
				{
					if(number.charAt(i) == answer.charAt(j))
					{
						rightB++;
					}
				}
			}
		}
		return String.format("%sA%sB", rightA,rightB);
	}

	/**
	 * 获取标准格式的时间
	 */
	public static String getStdDateTime(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	
	
}
