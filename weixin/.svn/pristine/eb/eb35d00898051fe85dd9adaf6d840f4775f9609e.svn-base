package com.xxcb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 输入邀请码记录
 * @author lidu
 * 2015-10-19
 */
@Entity
@Table(name = "wx_invitationrecord")
public class InvitationRecord {
	@Id
	@GeneratedValue(generator = "invitationrecord_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "invitationrecord_seq", sequenceName = "S_wx_invitationrecord")
	@Column(name = "RID")
	private Integer rid; //id
	
	private String userid;
	
	private String nickname;
	
	private String inviterUserid;
	
	private String inviterNickname;
	
	private String invitationCode;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getInviterUserid() {
		return inviterUserid;
	}

	public void setInviterUserid(String inviterUserid) {
		this.inviterUserid = inviterUserid;
	}

	public String getInviterNickname() {
		return inviterNickname;
	}

	public void setInviterNickname(String inviterNickname) {
		this.inviterNickname = inviterNickname;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
}
