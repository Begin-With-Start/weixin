package com.xxcb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 积分表
 * @author lidu
 * 2015-10-19
 */
@Entity
@Table(name = "wx_scorecode")
public class ScoreCode {
	@Id
	@GeneratedValue(generator = "scorecode_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "scorecode_seq", sequenceName = "S_wx_scorecode")
	@Column(name = "RID")
	private Integer rid; //id
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private String userId;
	
	private String nickName;

	private String invitationCode;
	
	private int score;
}
