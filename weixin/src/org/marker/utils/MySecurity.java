package org.marker.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 这里是加密算法
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 * */
public class MySecurity {

	
	public static final String SHA_1 = "SHA-1";
	
	public static final String MD5 = "MD5";
 
	
	
	
	public String encode(String strSrc, String encodeType) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			if (encodeType == null || "".equals(encodeType))
				encodeType = MD5;//默认使用MD5
			md = MessageDigest.getInstance(encodeType);
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return strSrc;
		}
		return strDes;
	}

	public String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static void main(String[] args) {
		MySecurity te = new MySecurity();
		String strSrc = "可以加密汉字";
		System.out.println("Source String:" + strSrc);
		System.out.println("Encrypted String:");
		System.out.println("Use MD5:" + te.encode(strSrc, null));
		System.out.println("Use MD5 test: " + te.encode("appid=wxe5976fada5d432e3&attach=xyt&body=英语&device_info=WEB&fee_type=CNY&goods_tag=XYT&limit_pay=no_credit&mch_id=1365949902&nonce_str=0.4105499488751514&notify_url=www.baidu.com&openid=oo-KAs_n8AL0PSWW4UzaMvagYGjk&out_trade_no=xyt14694288598827423725210125&product_id=math&spbill_create_ip=127.0.0.1&time_expire=20160725144379&time_start=20160725144059&total_fee=10&trade_type=JSAPI&key=05347148538346029poiuytrewqLKJHG", "MD5"));
		System.out.println("Use SHA:" + te.encode(strSrc, "SHA-1"));
		System.out.println("Use SHA-256:" + te.encode(strSrc, "SHA-256"));
	}
}