package com.xxcb.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "AccessToken")
public class AccessToken {
	
	@Id
	@GeneratedValue(generator = "AccessToken_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "AccessToken_seq", sequenceName = "s_accesstoken")
	@Column(name = "RID")
	private Integer rid; //id
	
	@Column(name = "ATNAME")
	private String name;

	@Column(name = "ACCESSTOKEN")
	private String accessToken;
	
	@Column(name = "ATTIME")
	private Date time;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
