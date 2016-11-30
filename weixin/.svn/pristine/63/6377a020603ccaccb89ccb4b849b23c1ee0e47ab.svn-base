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
 * 积分兑换项目
 * @author lidu
 * 2015-11-16
 */
@Entity
@Table(name = "wx_jfdh")
public class Jfdh {
	@Id
	@GeneratedValue(generator = "wx_jfdh_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "wx_jfdh_seq", sequenceName = "S_wx_jfdh")
	private Integer rid; //id
	
	private Integer xmid; //项目id
	
	private String tupian;//图片地址
	
	private String dbt;//大标题
	
	private String xbt;//小标题
	
	private String jiage;//价格
	
	private String fenshu;//份数
	
	private Date startTime;//开始时间
	
	private Date endTime;//结束时间
	
	private int sort;//序号
	
	private int total; //总份数

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
