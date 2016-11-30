package com.xyt.po;

import java.util.Date;

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
 * 用户加入群组记录
 * @author Administrator
 *
 */
@Entity
@Table(name = "XytUserInGroup")
public class XytUserInGroup {
	private Integer rid;
	
	//加入时间
	private Date joinDate;
	
	//粉丝信息
	private XytUserInfo xytUserInfo;
	
	//群组信息
	private XytGroupOrder group;
	
	//对应的订单信息
	private XytOrder xytOrder;
	
	//是否已移除加入群组的记录
	private boolean isRemove;

	@Id
	@GeneratedValue(generator = "XytUserInGroup_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "XytUserInGroup_seq", sequenceName = "s_xytUserInGroup")
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Column(nullable = false)
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	public XytUserInfo getXytUserInfo() {
		return xytUserInfo;
	}

	public void setXytUserInfo(XytUserInfo xytUserInfo) {
		this.xytUserInfo = xytUserInfo;
	}

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	public XytGroupOrder getGroup() {
		return group;
	}

	public void setGroup(XytGroupOrder group) {
		this.group = group;
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
	public boolean isRemove() {
		return isRemove;
	}

	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

}
