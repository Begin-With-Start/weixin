package org.marker.config;
/**
 * 大学伽配置信息
 * @author Administrator
 *
 */
public interface DxjConfig {
		//开发者微信号
		String appName = "gh_aa00eb30f3ec";
	
		// 赋权类型 
		String grant_type = "client_credential";
		
		// 修改为开发者申请的appid
		String APPID      = "wxf6872c94e403d7a7";
		
		// 修改为开发者申请的secret密钥
		String SECRET     = "ecdca7925bbcd0a5805b9fb0abab6334";
		
		//消息加密秘钥
		String EncodingAESKey = "Ifxbwlexdw8INenl5Syuof6bfHynGNu5mQhh1vHWaL4";
		
		//安全密钥
		String key = "05347148538346029poiuytrewqLKJHG";
		
		//商户号
		String mch_id = "1365949902";
		
		//日志目录
		String logPath = "C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/weixin/WEB-INF/log4j.properties";
		
		//商品描述
		String body = "xyt-course";
		
		//支付回调通知地址
		String notify_url = "http://222.240.171.100/weixin/dxj/DxjNotifyFromWxAction!OrderNotifyFromWx.action";
}
