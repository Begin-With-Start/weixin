package com.dxj.po;

import java.math.BigDecimal;

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

/**
 * 旅游线路订单表
 * @author lidu
 *
 */
@Entity
@Table(name = "DxjTravelRouteOrder")
public class DxjTravelRouteOrder {
	private Integer id;
	
	//线路
	private DxjTravelRoute route;
	
	//学生
	private XytUserInfo xytuserinfo;
	
	//费用
	private BigDecimal fee;
	
	//是否付款
	private boolean isPaied;
		
	//是否为积分兑换
	private boolean isPayByScore = false;

	@Id
	@GeneratedValue(generator = "DxjTravelRouteOrder_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "DxjTravelRouteOrder_seq", sequenceName = "s_DxjTravelRouteOrder")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public DxjTravelRoute getRoute() {
		return route;
	}

	public void setRoute(DxjTravelRoute route) {
		this.route = route;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public XytUserInfo getXytuserinfo() {
		return xytuserinfo;
	}

	public void setXytuserinfo(XytUserInfo xytuserinfo) {
		this.xytuserinfo = xytuserinfo;
	}

	@JoinColumn(nullable = false)
	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	@JoinColumn(nullable = false)
	public boolean isPaied() {
		return isPaied;
	}

	public void setPaied(boolean isPaied) {
		this.isPaied = isPaied;
	}

	@JoinColumn(nullable = true)
	public boolean isPayByScore() {
		return isPayByScore;
	}

	public void setPayByScore(boolean isPayByScore) {
		this.isPayByScore = isPayByScore;
	}
	
}
