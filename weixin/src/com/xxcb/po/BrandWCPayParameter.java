package com.xxcb.po;
/**
 * getBrandWCPayRequest参数
 * @author lidu
 *
 */

public class BrandWCPayParameter {
	public String appId;
	
	public String timeStamp;
	
	public String nonceStr;
	
	public String packAge;
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackAge() {
		return packAge;
	}

	public void setPackAge(String packAge) {
		this.packAge = packAge;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String signType;
	
	public String paySign;
}
