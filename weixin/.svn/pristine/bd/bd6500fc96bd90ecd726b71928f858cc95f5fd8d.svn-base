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
 * 中奖纪录表
 * @author zhangpeng
 * 2015-6-9
 */
@Entity
@Table(name = "Zjjl")
public class Zjjl {
	@Id
	@GeneratedValue(generator = "zjjl_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "zjjl_seq", sequenceName = "S_ZJJL")
	@Column(name = "RID")
	private Integer rid; //id
	
	@Column(name = "xmid")
	private Integer xmid;//项目id
	
	@Column(name = "userid")
	private String userid;//user openid

	@Column(name = "tel")
	private String tel;//电话

	@Column(name = "zjlx")
	private String zjlx;//中奖类型 0:大奖 1：一等奖 2：二等奖 3：三等奖

	@Column(name = "zjsj")
	private Date zjsj;//中奖时间
	
	@Column(name = "ZJBM")
	private String zjbm;//中奖编码
	
	@Column(name = "sflq")
	private String sflq;//是否领取
	
	@Column(name = "jzsj")
	private Date jzsj;//截止时间
	
	@Column(name = "yhqbm")
	private String yhqbm;//优惠券编码
	
	@Column(name = "yhqmm")
	private String yhqmm;//优惠券密码
	
	public String getYhqmm() {
		return yhqmm;
	}

	public void setYhqmm(String yhqmm) {
		this.yhqmm = yhqmm;
	}

	public String getYhqbm() {
		return yhqbm;
	}

	public void setYhqbm(String yhqbm) {
		this.yhqbm = yhqbm;
	}

	public String getSflq() {
		return sflq;
	}

	public void setSflq(String sflq) {
		this.sflq = sflq;
	}

	public Date getJzsj() {
		return jzsj;
	}

	public void setJzsj(Date jzsj) {
		this.jzsj = jzsj;
	}

	public String getZjbm() {
		return zjbm;
	}

	public void setZjbm(String zjbm) {
		this.zjbm = zjbm;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getXmid() {
		return xmid;
	}

	public void setXmid(Integer xmid) {
		this.xmid = xmid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZjlx() {
		return zjlx;
	}

	public Date getZjsj() {
		return zjsj;
	}

	public void setZjsj(Date zjsj) {
		this.zjsj = zjsj;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	
	
}
