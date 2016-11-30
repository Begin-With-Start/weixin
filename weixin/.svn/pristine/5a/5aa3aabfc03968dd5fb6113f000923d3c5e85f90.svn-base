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
 * 抢购项目
 * @author lidu
 * 2015-9-30
 */
@Entity
@Table(name = "wx_qgxm")
public class Qgxm {
	@Id
	@GeneratedValue(generator = "wx_qgxm_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "wx_qgxm_seq", sequenceName = "S_wx_qgxm")
	@Column(name = "xmid")
	private Integer xmid; //项目id
	
	@Column(name = "tupian")
	private String tupian;//图片地址
	
	@Column(name = "dbt")
	private String dbt;//大标题

	@Column(name = "xbt")
	private String xbt;//小标题

	@Column(name = "jiage")
	private String jiage;//价格

	public Integer getXmid() {
		return xmid;
	}

	public void setXmid(Integer xmid) {
		this.xmid = xmid;
	}

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	public String getDbt() {
		return dbt;
	}

	public void setDbt(String dbt) {
		this.dbt = dbt;
	}

	public String getXbt() {
		return xbt;
	}

	public void setXbt(String xbt) {
		this.xbt = xbt;
	}

	public String getJiage() {
		return jiage;
	}

	public void setJiage(String jiage) {
		this.jiage = jiage;
	}

	public String getFenshu() {
		return fenshu;
	}

	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}

	public Date getQgsj() {
		return qgsj;
	}

	public void setQgsj(Date qgsj) {
		this.qgsj = qgsj;
	}

	public String getCyrs() {
		return cyrs;
	}

	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}

	@Column(name = "fenshu")
	private String fenshu;//份数

	@Column(name = "qgsj")
	private Date qgsj;//开奖时间

	@Column(name = "cyrs")
	private String cyrs;//暂时作为现价格
}
