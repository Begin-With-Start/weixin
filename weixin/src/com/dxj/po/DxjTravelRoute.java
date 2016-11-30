package com.dxj.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 旅游线路表
 * @author lidu
 *
 */
@Entity
@Table(name = "DxjTravelRoute")
public class DxjTravelRoute {
	private Integer id;
	
	//线路名称
	private String travelRouteName;
	
	//开始时间
	private Date startDate;
	
	//图片地址
	private String pictureUrl;
	
	//微信图文链接地址
	private String weixinUrl;
	
	//费用
	private BigDecimal fee;
	
	//是否打折
	private boolean isDiscount;
	
	//打折数
	private Float discountNumber;
	
	//报名人数
	private Integer appliedNumber;
		
	//点赞数
	private Integer likePointNumber;
		
	//线路简介
	private String introduction;
	
	//是否支持积分兑换
	private boolean isPayByScore = false;
		
	//积分兑换所需积分
	private Integer scoreApplied;
	
	//社团
	private Integer isGroup;

	@Id
	@GeneratedValue(generator = "DxjTravelRoute_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "DxjTravelRoute_seq", sequenceName = "s_DxjTravelRoute")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getTravelRouteName() {
		return travelRouteName;
	}

	public void setTravelRouteName(String travelRouteName) {
		this.travelRouteName = travelRouteName;
	}

	@Column(nullable = true)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(nullable = false)
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Column(nullable = true)
	public String getWeixinUrl() {
		return weixinUrl;
	}

	public void setWeixinUrl(String weixinUrl) {
		this.weixinUrl = weixinUrl;
	}

	@Column(nullable = true)
	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	@Column(nullable = false)
	public boolean isDiscount() {
		return isDiscount;
	}

	public void setDiscount(boolean isDiscount) {
		this.isDiscount = isDiscount;
	}

	@Column(nullable = true)
	public Float getDiscountNumber() {
		return discountNumber;
	}

	public void setDiscountNumber(Float discountNumber) {
		this.discountNumber = discountNumber;
	}

	@Column(nullable = false)
	public Integer getAppliedNumber() {
		return appliedNumber;
	}

	public void setAppliedNumber(Integer appliedNumber) {
		this.appliedNumber = appliedNumber;
	}

	@Column(nullable = true)
	public Integer getLikePointNumber() {
		return likePointNumber;
	}

	public void setLikePointNumber(Integer likePointNumber) {
		this.likePointNumber = likePointNumber;
	}

	@Column(nullable = true)
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(nullable = true)
	public boolean isPayByScore() {
		return isPayByScore;
	}

	public void setPayByScore(boolean isPayByScore) {
		this.isPayByScore = isPayByScore;
	}

	@Column(nullable = true)
	public Integer getScoreApplied() {
		return scoreApplied;
	}

	public void setScoreApplied(Integer scoreApplied) {
		this.scoreApplied = scoreApplied;
	}

	@Column(nullable = true)
	public Integer getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Integer isGroup) {
		this.isGroup = isGroup;
	}
	
	
}
