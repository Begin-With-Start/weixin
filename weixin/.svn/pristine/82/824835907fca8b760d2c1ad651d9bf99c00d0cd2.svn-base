package com.xxcb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 统计订阅号按键点击次数
 * @author lidu
 * @date 2015.8.13
 */

@Entity
@Table(name = "WX_BUTTON1")
public class Button1 {
	@Id
	@GeneratedValue(generator = "Button1_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "Button1_seq", sequenceName = "S_WX_BUTTON1")
	@Column(name = "RID")
	private Integer rid; //id
	
	@Column(name = "TOTALCLICK")
	private int totalclick;
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public int getTotalclick() {
		return totalclick;
	}

	public void setTotalclick(int totalclick) {
		this.totalclick = totalclick;
	}

	public String getClickdate() {
		return clickdate;
	}

	public void setClickdate(String clickdate) {
		this.clickdate = clickdate;
	}

	@Column(name = "CLICKDATE")
	private String clickdate;
}
