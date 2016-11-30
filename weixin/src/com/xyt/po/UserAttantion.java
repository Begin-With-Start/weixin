package com.xyt.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户关注
 * @author ChenPengFei
 *
 */
@Entity
@Table(name = "UserAttantion")
public class UserAttantion {

	 private Integer uaid;
     private XytUserInfo user;
     private XytUserInfo attantionUser;
     private Date createTime;
     


    @Id
 	@GeneratedValue(generator = "UserAttantion_seq", strategy = GenerationType.SEQUENCE)
 	@SequenceGenerator(name = "UserAttantion_seq", sequenceName = "s_userAttantion")
    public Integer getUaid() {
        return this.uaid;
    }
    
    public void setUaid(Integer uaid) {
        this.uaid = uaid;
    }

    @Column(nullable = false)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public XytUserInfo getUser() {
		return user;
	}

	public void setUser(XytUserInfo user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public XytUserInfo getAttantionUser() {
		return attantionUser;
	}

	public void setAttantionUser(XytUserInfo attantionUser) {
		this.attantionUser = attantionUser;
	}

	


	
}
