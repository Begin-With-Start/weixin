package org.marker.weixin.test;

import javax.servlet.http.HttpServlet;

import net.sf.json.JSONObject;

import org.marker.utils.HttpUtil;
import org.marker.utils.MenuUtilTest;
import org.marker.weixin.exception.WeixinException;

@SuppressWarnings("serial")
public class LongUrlToShortTest extends HttpServlet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenuUtilTest menuUtilTest = new MenuUtilTest();
		String access_token = null;
		String url = "http://mp.weixin.qq.com/s?__biz=MzA5NDg0ODk2OQ==&mid=401357424&idx=1&sn=90f7fbef47b4f26aa46990f562cc1548#rd";
		String shortUrl = null;
		try {
			access_token = menuUtilTest.getAccessToken();
		} catch (WeixinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(access_token);
		
		String requestUrlMedia = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
		requestUrlMedia = requestUrlMedia.replace("ACCESS_TOKEN", access_token);
		String jsonMsg = "{\"action\":\"long2short\",\"long_url\":\"%s\"}";
		JSONObject jsonObject = HttpUtil.httpsRequest(requestUrlMedia, "POST", String.format(jsonMsg,url));
		if (null != jsonObject) {
            try {
            	shortUrl = jsonObject.getString("short_url");
            } catch (Exception e) {
                int errorCode = jsonObject.getInt("errcode");
                System.out.println(errorCode);
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println(errorMsg);
            }
        }
		System.out.println(shortUrl);
	}

}
