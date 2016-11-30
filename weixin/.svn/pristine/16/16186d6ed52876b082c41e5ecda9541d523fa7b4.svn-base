package com.xyt.po;

import java.util.Date;

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
 * 校园通积分信息
 */
@Entity
@Table(name = "XytScoreInfo")
public class XytScoreInfo {
	
	private Integer rid; //id
	
	private XytUserInfo selfUserInfo;
	
	private XytUserInfo otherUserInfo;
	
	private String scoreCode;
	
	private Integer scoreOption;
	
	private Date createTime;
	
	private boolean status;
	
	private String description;
	
	//是否用于支付，1为支付，0为加分
	private boolean isPay;
	
	private XytCourse xytCourse;

	@Id
	@GeneratedValue(generator = "XytScoreInfo_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "XytScoreInfo_seq", sequenceName = "s_xytScoreInfo")
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public XytUserInfo getSelfUserInfo() {
		return selfUserInfo;
	}

	public void setSelfUserInfo(XytUserInfo selfUserInfo) {
		this.selfUserInfo = selfUserInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	public XytUserInfo getOtherUserInfo() {
		return otherUserInfo;
	}

	public void setOtherUserInfo(XytUserInfo otherUserInfo) {
		this.otherUserInfo = otherUserInfo;
	}

	@JoinColumn(nullable = false)
	public String getScoreCode() {
		return scoreCode;
	}

	public void setScoreCode(String scoreCode) {
		this.scoreCode = scoreCode;
	}

	@JoinColumn(nullable = false)
	public Integer getScoreOption() {
		return scoreOption;
	}

	public void setScoreOption(Integer scoreOption) {
		this.scoreOption = scoreOption;
	}

	@JoinColumn(nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JoinColumn(nullable = false)
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@JoinColumn(nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JoinColumn(nullable = false)
	public boolean isPay() {
		return isPay;
	}

	public void setPay(boolean isPay) {
		this.isPay = isPay;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	public XytCourse getXytCourse() {
		return xytCourse;
	}

	public void setXytCourse(XytCourse xytCourse) {
		this.xytCourse = xytCourse;
	}
	
	
}
