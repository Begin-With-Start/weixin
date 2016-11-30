package com.dxj.po;

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

import com.xyt.po.XytUserInfo;

@Entity
@Table(name = "DxjPayRecord")
public class DxjPayRecord {
	
	private Integer id;
	
	//用户openid
	private String openid;
	
	private XytUserInfo xytUserInfo;
	
	private Integer totalFee;
	
	private String transactionId;
	
	private String endTime;
	
	private DxjTravelRouteOrder routeOrder;
	
	private String isSubscribe;

	@Id
	@GeneratedValue(generator = "DxjPayRecord_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "DxjPayRecord_seq", sequenceName = "s_DxjPayRecord")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@JoinColumn(nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	public XytUserInfo getXytUserInfo() {
		return xytUserInfo;
	}

	public void setXytUserInfo(XytUserInfo xytUserInfo) {
		this.xytUserInfo = xytUserInfo;
	}

	@Column(nullable = false)
	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	@Column(nullable = true)
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Column(nullable = false)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@JoinColumn(nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	public DxjTravelRouteOrder getRouteOrder() {
		return routeOrder;
	}

	public void setRouteOrder(DxjTravelRouteOrder routeOrder) {
		this.routeOrder = routeOrder;
	}

	@Column(nullable = true)
	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	
	
}
