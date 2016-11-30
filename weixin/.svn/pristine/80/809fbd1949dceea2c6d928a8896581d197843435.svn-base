package com.xyt.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 校园通AccessToken
 * @author Administrator
 *
 */
@Entity
@Table(name = "XytAccessToken")
public class XytAccessToken {
	
	private Integer rid; //id
	
	
	private String name;
	
	
	private String accessToken;
	
	
	private Date time;

	@Id
	@GeneratedValue(generator = "XytAccessToken_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "XytAccessToken_seq", sequenceName = "s_xytAccessToken")
	public Integer getRid() {
		return rid;
	}


	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Column(nullable = true)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Column(nullable = false)
	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
