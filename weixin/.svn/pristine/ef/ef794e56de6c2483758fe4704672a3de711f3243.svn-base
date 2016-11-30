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
 * 抽奖项目
 * @author zhangpeng
 * 2015-6-9
 */
@Entity
@Table(name = "Cjxm")
public class Cjxm {
	@Id
	@GeneratedValue(generator = "cjxm_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "cjxm_seq", sequenceName = "S_Cjxm")
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

	@Column(name = "fenshu")
	private String fenshu;//份数

	@Column(name = "kjsj")
	private Date kjsj;//开奖时间

	@Column(name = "cyrs")
	private String cyrs;//抽奖人数

	@Column(name = "kssj")
	private Date kssj;//开始时间

	@Column(name = "jssj")
	private Date jssj;//开始时间

	
	
	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}

	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}

	private Integer sort;
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

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

	public Date getKjsj() {
		return kjsj;
	}

	public void setKjsj(Date kjsj) {
		this.kjsj = kjsj;
	}

	public String getCyrs() {
		return cyrs;
	}

	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}
	
	
}
