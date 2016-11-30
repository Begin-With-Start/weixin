package com.xxcb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "WX_BUTTON3")
public class Button3 {
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

	@Id
	@GeneratedValue(generator = "Button3_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "Button3_seq", sequenceName = "S_WX_BUTTON3")
	@Column(name = "RID")
	private Integer rid; //id
	
	@Column(name = "TOTALCLICK")
	private int totalclick;
	
	@Column(name = "CLICKDATE")
	private String clickdate;
}
