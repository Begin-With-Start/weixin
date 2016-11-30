package org.marker.weixin.test;

import org.marker.utils.HttpUtil;

public class HttpTest {

	static String ACCESS_TOKEN ="hnzhenhaowan2015";
	static String appID = "wxb77ebe16b2abdd83";
	static String appsecret = "ca3ef339ee50485541b63151f0b8848a";
	
	
	public static void main(String[] args) {
//		String a = "https://api.weixin.qq.com/cgi-bin/token?"
//				+ "grant_type=client_credential&appid="+appID+"&secret="+appsecret;
//		
//		
//		String json = HttpUtil.sendHttpsGET(a);
//		System.out.println(json);
		
		
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
		url = url + ACCESS_TOKEN;
		
		
		 StringBuffer sb = new StringBuffer();
	        sb.append("{");
	        sb.append(" \"button\":[");
	        sb.append("     {");
	        sb.append("         \"name\":\"中啊\",");       //第一个菜单
	        sb.append("         \"type\":\"click\","); 
	        sb.append("         \"key\":\"M1\""); 
	        sb.append("     }"); 
	        sb.append(" ]");
	        sb.append("}");
		String result = HttpUtil.sendHttpsPOST(url , sb.toString());
		System.out.println(result);
	}
}
