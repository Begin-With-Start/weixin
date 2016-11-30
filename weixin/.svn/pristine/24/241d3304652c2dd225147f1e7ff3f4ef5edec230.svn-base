package com.xxcb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 在微信用户的基本信息基础上添加电话号码字段
 * @author lidu
 * @date 2015.8.3
 */

@Entity
@Table(name = "wx_userinfo")
public class UserInfo {
	@Id
	@GeneratedValue(generator = "userinfo_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "userinfo_seq", sequenceName = "S_WX_USERINFO")
	@Column(name = "RID")
	private Integer rid; //id
	
	//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private int subscribe;
	
	//用户的标识
	private String openid;
	
	//昵称
	private String nickname;
	
	//性别
	private int sex;
	
	//所在的城市
	private String city;
	
	//所在的国家
	private String country;
	
	//所在的省份
	private String province;
	
	//用户的语言
	private String language;
	
	//用户头像
	private String headimgurl;
	
	//用户关注时间，为时间戳
	private String subscribe_time;
	
	
	//公众号对粉丝的备注
	private String remark;
	
	//分组id
	private String groupid;
	
	//电话号码
	private String phonenumber;
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
