package com.xyt.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.marker.config.ConfigTest;
import org.marker.utils.HttpUtil;
import org.marker.utils.MD5util;

import com.xxcb.util.DateUtil;
import com.xxcb.weixin.SignUtilTest;
import com.xyt.po.XytCourse;
import com.xyt.po.XytOrder;

public class XytPayUtil {
	
	public static String getGroupOrderPrepayId(String openId, String addrip, XytOrder xytOrder)
	{
		PropertyConfigurator.configure(ConfigTest.logPath);
		final Logger logger  =  Logger.getLogger(XytPayUtil.class );
		//公众账号ID
		String appid = ConfigTest.APPID;
				
		//商户号
		String mch_id = ConfigTest.mch_id;
				
		//设备号
		String device_info = "WEB";
				
		String nonce_str = String.valueOf(Math.random());
				
		//商品描述
		String body = xytOrder.getXytCourse().getCourseName();
		
		//附加数据，保存订单rid
		String attach = xytOrder.getRid().toString();
		
		String out_trade_no = GenerateTradeNo();
		
		String fee_type = "CNY";
		int total_fee = convertFromBigDecimalToInt(xytOrder.getFee());
		String spbill_create_ip = addrip;
		String time_start = DateUtil.getNowTime().replace("-", "").replace(" ", "").replace(":", "");
		String time_expire = String.valueOf(Long.valueOf(time_start)+(long)320);
		String goods_tag = "XYT";
		String notify_url =ConfigTest.group_notify_url;
		String trade_type ="JSAPI";
		String product_id = xytOrder.getXytCourse().getCourseCode();
		String limit_pay = "no_credit";
		String openid = openId;
		String []array = {"appid=".concat(appid), "mch_id=".concat(mch_id), "device_info=".concat(device_info),
				"nonce_str=".concat(nonce_str), "body=".concat(body), 
				"attach=".concat(attach), "out_trade_no=".concat(out_trade_no), "fee_type=".concat(fee_type), 
				"total_fee=".concat(String.valueOf(total_fee)), 
				"spbill_create_ip=".concat(spbill_create_ip), "time_start=".concat(time_start), "time_expire=".concat(time_expire), 
				"goods_tag=".concat(goods_tag), "notify_url=".concat(notify_url), "trade_type=".concat(trade_type),
				"product_id=".concat(product_id), "limit_pay=".concat(limit_pay), "openid=".concat(openid)};
		
		SignUtilTest.sort(array);
		
		String stringA = new String() ;
		
		String stringSignTemp = new String();
				
		String sign = new String();
				
		String xmlStr = new String();
			
		String prepay_id = new String();
				
		String key = ConfigTest.key;
		
		for(int i = 0 ; i < array.length ; i++)
		{
			stringA = stringA.concat(array[i].concat("&"));
		}
		
		logger.error("stringA ="+stringA);
		
		stringSignTemp = stringA.concat("key=").concat(key);
		
		logger.error("stringSignTemp ="+stringSignTemp);
		
		sign = MD5util.MD5(stringSignTemp);
		
		logger.error("sign ="+sign);
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("appid", appid);
		paraMap.put("mch_id", mch_id);
		paraMap.put("device_info", device_info);
		paraMap.put("nonce_str", nonce_str);
		paraMap.put("sign", sign);
		paraMap.put("body", body);
		paraMap.put("attach", attach);
		paraMap.put("out_trade_no", out_trade_no);
		paraMap.put("fee_type", fee_type);
		paraMap.put("total_fee", String.valueOf(total_fee));
		paraMap.put("spbill_create_ip", spbill_create_ip);
		paraMap.put("time_start", time_start);
		paraMap.put("time_expire", time_expire);
		paraMap.put("goods_tag", goods_tag);
		paraMap.put("notify_url", notify_url);
		paraMap.put("trade_type", trade_type);
		paraMap.put("product_id", product_id);
		paraMap.put("limit_pay", limit_pay);
		paraMap.put("openid", openid);
		String xmlFromMap= ArrayToXml(paraMap);
		logger.error("xmlFromMap ="+xmlFromMap);
		
		String requestUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		xmlStr = HttpUtil.sendHttpsPOST(requestUrl, xmlFromMap);
		logger.error("xmlStr ="+xmlStr);
		
		String start = "<prepay_id><![CDATA[";
		String end = "]]></prepay_id>";
		prepay_id = xmlStr.substring(xmlStr.indexOf(start)+start.length(), xmlStr.indexOf(end));
		logger.error("prepay_id ="+prepay_id);		
		return prepay_id;
	}
	
	
	
	
	public static String getPrepayId(String openId, String addrip, XytCourse xytCourse) throws UnsupportedEncodingException
	{
		PropertyConfigurator.configure(ConfigTest.logPath);
		final Logger logger  =  Logger.getLogger(XytPayUtil.class );
		
		//公众账号ID
		String appid = ConfigTest.APPID;
		
		//商户号
		String mch_id = ConfigTest.mch_id;
		
		//设备号
		String device_info = "WEB";
		
		String nonce_str = String.valueOf(Math.random());
		
		//商品描述
		//String body = ConfigTest.body;
		String body = xytCourse.getCourseName();
		
		logger.error("body ="+xytCourse.getCourseName());
		
		//附加数据，保存课程id
		String attach = xytCourse.getRid().toString();
		
		String out_trade_no = GenerateTradeNo();
		
		logger.error("out_trade_no ="+out_trade_no);
		//货币类型  
		String fee_type = "CNY";
		
		int total_fee = 0;
		total_fee = convertFromBigDecimalToInt(xytCourse.getFee());
		if(xytCourse.isDiscount())
		{
			total_fee = (int) (total_fee*xytCourse.getDiscountNumber());
		}
		
		logger.error("total_fee ="+total_fee);
		
		String spbill_create_ip = addrip;
		
		logger.error("spbill_create_ip ="+spbill_create_ip);
		
		//交易起始时间
		String time_start = DateUtil.getNowTime().replace("-", "").replace(" ", "").replace(":", "");
		
		logger.error("time_start ="+time_start);
		String time_expire = String.valueOf(Long.valueOf(time_start)+(long)320);
		//商品标记(待完善)
		String goods_tag = "XYT";
		
		//通知地址(待完善)
		String notify_url =ConfigTest.personal_notify_url;
		
		String trade_type ="JSAPI";
		
		String product_id = xytCourse.getCourseCode();
		
		logger.error("product_id ="+product_id);
		
		String limit_pay = "no_credit";
		
		String openid = openId;
		
		logger.error("openid ="+openid);
		
		//签名(最后获取)
		String []array = {"appid=".concat(appid), "mch_id=".concat(mch_id), "device_info=".concat(device_info),
				"nonce_str=".concat(nonce_str), "body=".concat(body), 
				"attach=".concat(attach), "out_trade_no=".concat(out_trade_no), "fee_type=".concat(fee_type), 
				"total_fee=".concat(String.valueOf(total_fee)), 
				"spbill_create_ip=".concat(spbill_create_ip), "time_start=".concat(time_start), "time_expire=".concat(time_expire), 
				"goods_tag=".concat(goods_tag), "notify_url=".concat(notify_url), "trade_type=".concat(trade_type),
				"product_id=".concat(product_id), "limit_pay=".concat(limit_pay), "openid=".concat(openid)};
		
		SignUtilTest.sort(array);
		
		//组合成stringA
		String stringA = new String() ;
		
		String stringSignTemp = new String();
				
		String sign = new String();
				
		String xmlStr = new String();
			
		String prepay_id = new String();
				
		String key = ConfigTest.key;
		
		for(int i = 0 ; i < array.length ; i++)
		{
			stringA = stringA.concat(array[i].concat("&"));
		}
		
		logger.error("stringA ="+stringA);
		
		stringSignTemp = stringA.concat("key=").concat(key);
		
		logger.error("stringSignTemp ="+stringSignTemp);
		
		//对stringSignTemp进行MD5加密
				
		sign = MD5util.MD5(stringSignTemp);
		
		logger.error("sign ="+sign);
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("appid", appid);
		paraMap.put("mch_id", mch_id);
		paraMap.put("device_info", device_info);
		paraMap.put("nonce_str", nonce_str);
		paraMap.put("sign", sign);
		paraMap.put("body", body);
		paraMap.put("attach", attach);
		paraMap.put("out_trade_no", out_trade_no);
		paraMap.put("fee_type", fee_type);
		paraMap.put("total_fee", String.valueOf(total_fee));
		paraMap.put("spbill_create_ip", spbill_create_ip);
		paraMap.put("time_start", time_start);
		paraMap.put("time_expire", time_expire);
		paraMap.put("goods_tag", goods_tag);
		paraMap.put("notify_url", notify_url);
		paraMap.put("trade_type", trade_type);
		paraMap.put("product_id", product_id);
		paraMap.put("limit_pay", limit_pay);
		paraMap.put("openid", openid);
		String xmlFromMap= ArrayToXml(paraMap);
		logger.error("xmlFromMap ="+xmlFromMap);
		
		String requestUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		xmlStr = HttpUtil.sendHttpsPOST(requestUrl, xmlFromMap);
		logger.error("xmlStr ="+xmlStr);
		
		String start = "<prepay_id><![CDATA[";
		String end = "]]></prepay_id>";
		prepay_id = xmlStr.substring(xmlStr.indexOf(start)+start.length(), xmlStr.indexOf(end));
		logger.error("prepay_id ="+prepay_id);		
		return prepay_id;
		
	}
	
	public static int convertFromBigDecimalToInt(BigDecimal price)
	{
		BigDecimal intPrice = price.multiply(new BigDecimal(100));
		intPrice = intPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
		return intPrice.intValue();
	}
	
	/**
	 * 根据当前系统时间加随机序列来生成订单号
	 * 
	 */
	public static String GenerateTradeNo()
	{
		String nonce_str = String.valueOf(Math.random());
		String timeStamp = String.valueOf(System.currentTimeMillis()/1000);
		String tradeNo = "xyt".concat(timeStamp).concat(nonce_str.substring(2));
		return tradeNo;
	}
	
	/**
	 * map转成xml
	 * 
	 * @param arr
	 * @return
	 */
	public static String ArrayToXml(Map<String, String> arr) {
		String xml = "<xml>";

		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			xml += "<" + key + ">" + val + "</" + key + ">";
		}

		xml += "</xml>";
		return xml;
	}
}
