package com.xyt.po;

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
 * 支付记录
 * @author Administrator
 *
 */
@Entity
@Table(name = "XytPayRecord")
public class XytPayRecord {
	
	private Integer rid;
	
	//用户openid
	private String openid;
	
	private XytUserInfo xytUserInfo;
	
	private Integer totalFee;
	
	private String transactionId;
	
	private String endTime;
	
	private XytOrder xytOrder;
	
	private String isSubscribe;

	@Id
	@GeneratedValue(generator = "XytPayRecord_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "XytPayRecord_seq", sequenceName = "s_xytPayRecord")
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
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

	@Column(nullable = false)
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

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	public XytOrder getXytOrder() {
		return xytOrder;
	}

	public void setXytOrder(XytOrder xytOrder) {
		this.xytOrder = xytOrder;
	}

	@Column(nullable = false)
	public String isSubscribe() {
		return isSubscribe;
	}

	public void setSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	
	
}
