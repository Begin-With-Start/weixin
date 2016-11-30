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
 * 兑换纪录表
 * @author lidu
 * 2015-11-16
 */
@Entity
@Table(name = "wx_jfdhjl")
public class Jfdhjl {
	@Id
	@GeneratedValue(generator = "jfdhjl_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "jfdhjl_seq", sequenceName = "S_wx_jfdhjl")
	@Column(name = "RID")
	private Integer rid; //id
	
	private String addr;
	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDhsj() {
		return dhsj;
	}

	public void setDhsj(Date dhsj) {
		this.dhsj = dhsj;
	}

	@Column(name = "xmid")
	private Integer xmid;//项目id
	
	@Column(name = "userid")
	private String userid;//user openid

	@Column(name = "tel")
	private String tel;//电话
	
	@Column(name = "name")
	private String name;//电话

	@Column(name = "dhsj")
	private Date dhsj;

}
