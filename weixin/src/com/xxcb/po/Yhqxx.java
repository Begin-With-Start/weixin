package com.xxcb.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 优惠券信息表
 * @author lidu
 * @date 2015.7.17
 */
@Entity
@Table(name = "yhqxx")
public class Yhqxx {
	@Id
	@GeneratedValue(generator = "yhqxx_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "yhqxx_seq", sequenceName = "S_YHQXX")
	@Column(name = "RID")
	private Integer rid; //id
	
	private String yhqbm;//优惠券编码
	
	private String yhqmm;//优惠券密码
	
	private String yhqje;//优惠券金额
	
	private Date startdate;//优惠券生效日期
	
	private Date enddate;//优惠券终止日期
	
	private String sfsy;//是否使用标识
	
	private String sfcj;//是否已被抽奖

	public String getSfcj() {
		return sfcj;
	}

	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getYhqbm() {
		return yhqbm;
	}

	public void setYhqbm(String yhqbm) {
		this.yhqbm = yhqbm;
	}

	public String getYhqmm() {
		return yhqmm;
	}

	public void setYhqmm(String yhqmm) {
		this.yhqmm = yhqmm;
	}

	public String getYhqje() {
		return yhqje;
	}

	public void setYhqje(String yhqje) {
		this.yhqje = yhqje;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getSfsy() {
		return sfsy;
	}

	public void setSfsy(String sfsy) {
		this.sfsy = sfsy;
	}
}
