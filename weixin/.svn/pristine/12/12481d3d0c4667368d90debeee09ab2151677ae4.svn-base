package com.xxcb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmusingService {
	/** 
	 * 发起http get请求获取网页源代码 
	 *  
	 * @param requestUrl 
	 * @return String
	 * @author lidu
	 * @date 2015.8.12
	 */  
	private static String httpRequest(String requestUrl) {
		StringBuffer buffer = null;  
		try{
			URL url = new URL(requestUrl);  
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			
			httpUrlConn.setDoInput(true);  
			httpUrlConn.setRequestMethod("GET");  
			
			// 获取输入流  
			InputStream inputStream = httpUrlConn.getInputStream();  
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
			
			// 读取返回结果 
			buffer = new StringBuffer();  
			String str = null;  
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);  
			}
			
			// 释放资源 
			bufferedReader.close();  
			inputStreamReader.close();  
			inputStream.close();  
			httpUrlConn.disconnect();  
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	/**
	 * 从html中抽取历史上的今天信息
	 * @author lidu
	 * @date 2015.8.12
	 */
	private static String extract(String html) {
		StringBuffer buffer = null;
		// 日期标签：区分是昨天还是今天  
		String dateTag = getMonthDay(0);  
		Pattern p = Pattern.compile("(.*)(<div class=\"listren\">)(.*?)(</div>)(.*)");
		Matcher m = p.matcher(html);  
		if (m.matches()) {
			buffer = new StringBuffer();  
			if (m.group(3).contains(getMonthDay(-1))){
				dateTag = getMonthDay(-1);  
			}  
			
			// 拼装标题 
			buffer.append("≡ ").append("历史上的").append(dateTag).append(" ≡").append("\n\n");  
			// 抽取需要的数据
			for (String info : m.group(3).split("  ")) { 
				info = info.replace(dateTag, "").replace("（图）", "").replaceAll("</?[^>]+>", "").replaceAll("&nbsp;&nbsp", "").trim();  
				
				// 在每行末尾追加2个换行符
				if (!"".equals(info)) {
					buffer.append(info).append("\n\n");  

				}
			}
		}
		// 将buffer最后两个换行符移除并返回   
		return (null == buffer) ? null : buffer.substring(0, buffer.lastIndexOf("\n\n"));  
	}
	
	/**
	 * 获取前后n天日期
	 * @author lidu
	 * @date 2015.8.12
	 */
	private static String getMonthDay(int diff) {  
		DateFormat df = new SimpleDateFormat("M月d日"); 
		Calendar c = Calendar.getInstance();  
		c.add(Calendar.DAY_OF_YEAR, diff);  
		return df.format(c.getTime());  
	}
	
	/**
	 * 封装历史上的今天查询方法，供外部调用
	 * @author lidu
	 * @date 2015.8.12
	 */
	 public static String getTodayInHistoryInfo() {
		// 获取网页源代码
		 String html = httpRequest("http://www.rijiben.com/");  
		 String result = extract(html);  
		 return result;  
	 }
	
	 /**
	 * 从html中抽取笑话的信息信息
	 * @author lidu
	 * @date 2015.8.12
	 */
	 private static String extract1(String html) {
		 StringBuffer buffer = null;
		 Pattern p1 = Pattern.compile("(<li class=\"list_li\">)");
		 Matcher m1 = p1.matcher(html);  
		 
		 Pattern p2 = Pattern.compile("(</div>)");
		 Matcher m2 = p2.matcher(html);
		 
		 List<String> list = new ArrayList<String>();
		 int start = 0; 
		 while(m1.find(start))
		 {
			 boolean found = m2.find(m1.end());
			 String str = html.substring(m1.end(), m2.start()).replace("<div class=\"content\">", "    ").replace("&quot;", "\"");
			 start = m1.end();
			 list.add(str);
		 }
		 int number = (int)(Math.random()*(list.size()-1));
		 return list.get(number);
	 }
	 
	 
	 /**
	 * 封装历史上的今天查询方法，供外部调用
	 * @author lidu
	 * @date 2015.8.12
	 */
	 public static String getjoke() {
		// 获取网页源代码
		 String html = httpRequest("http://ishuo.cn/joke/");  
		 String result = extract1(html);  
		 return result;  
	 }
	 
	 
	 public static void main(String[] args) {  
		 String info = getTodayInHistoryInfo();  
		 System.out.println(info);  
	 }
	 
}
