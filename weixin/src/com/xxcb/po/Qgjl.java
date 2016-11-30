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
 * 抢购纪录表
 * @author lidu
 * 2015-10-19
 */
@Entity
@Table(name = "wx_qgjl")
public class Qgjl {
	@Id
	@GeneratedValue(generator = "qgjl_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "qgjl_seq", sequenceName = "S_wx_qgjl")
	@Column(name = "RID")
	private Integer rid; //id
	
	@Column(name = "xmid")
	private Integer xmid;//项目id
	
	@Column(name = "userid")
	private String userid;//user openid

	@Column(name = "tel")
	private String tel;//电话
	
	@Column(name = "name")
	private String name;//电话

	private Date qgsj;

	private String addr;
	
	private String fenshu;//抢购的份数
	
	public String getFenshu() {
		return fenshu;
	}

	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getQgsj() {
		return qgsj;
	}

	public void setQgsj(Date qgsj) {
		this.qgsj = qgsj;
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
}
