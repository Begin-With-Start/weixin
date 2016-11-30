package com.xyt.po;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 校园通用户信息
 * @author Administrator
 *
 */
@Entity
@Table(name = "XytUserInfo")
public class XytUserInfo {
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
	
	//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	private String unionid;
	
	//公众号对粉丝的备注
	private String remark;
			
	//分组id
	private String groupid;
	
	//姓名
	private String name;
	
	//电话号码
	private String telNumber;
	
	//年龄
	private int age;
	
	//学校
	private XytCollege college;
	
	//报名课程数
	private int courseSum;
	
	//消费总额
	private BigDecimal consumeSum;
	
	//密码
	private String code;
	
	//积分编码
	private String scoreCode;
	
	//是否已输入邀请码
	private boolean hasInputScoreCode = false;
	
	//输入的邀请码
	private String inputScoreCode;
	
	//关注的公众号
	private String weixin;
	
	
	@Column(nullable = true)
	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	@Id
	@GeneratedValue(generator = "XytUserInfo_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "XytUserInfo_seq", sequenceName = "s_xytuserinfo")
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Column(nullable = true)
	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	@Column(nullable = true)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(nullable = true)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(nullable = true)
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(nullable = true)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(nullable = true)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(nullable = true)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(nullable = true)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(nullable = true)
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Column(nullable = true)
	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	@Column(nullable = true)
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Column(nullable = true)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(nullable = true)
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@Column(nullable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = true)
	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	@Column(nullable = true)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	public XytCollege getCollege() {
		return college;
	}

	public void setCollege(XytCollege college) {
		this.college = college;
	}

	@Column(nullable = true)
	public int getCourseSum() {
		return courseSum;
	}

	public void setCourseSum(int courseSum) {
		this.courseSum = courseSum;
	}

	@Column(nullable = true)
	public BigDecimal getConsumeSum() {
		return consumeSum;
	}

	public void setConsumeSum(BigDecimal consumeSum) {
		this.consumeSum = consumeSum;
	}

	@Column(nullable = true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(nullable = true)
	public String getScoreCode() {
		return scoreCode;
	}

	public void setScoreCode(String scoreCode) {
		this.scoreCode = scoreCode;
	}

	@Column(nullable = false)
	public boolean isHasInputScoreCode() {
		return hasInputScoreCode;
	}

	public void setHasInputScoreCode(boolean hasInputScoreCode) {
		this.hasInputScoreCode = hasInputScoreCode;
	}

	@Column(nullable = true)
	public String getInputScoreCode() {
		return inputScoreCode;
	}

	public void setInputScoreCode(String inputScoreCode) {
		this.inputScoreCode = inputScoreCode;
	}
	
	
}
