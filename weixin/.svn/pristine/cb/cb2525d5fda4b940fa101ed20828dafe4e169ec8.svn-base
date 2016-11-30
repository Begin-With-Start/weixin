package com.xxcb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 统计洗脚次数
 * @author lidu
 * @date 2015.9.14
 */
@Entity
@Table(name = "WX_FootbathRecord")
public class FootbathRecord {
	@Id
	@GeneratedValue(generator = "FootbathRecord_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FootbathRecord_seq", sequenceName = "S_WX_FootbathRecord")
	@Column(name = "RID")
	private Integer rid; //id
	
	private String openId;
	
	private String nickName;
	
	private String helper;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHelper() {
		return helper;
	}

	public void setHelper(String helper) {
		this.helper = helper;
	}
	
	
}
