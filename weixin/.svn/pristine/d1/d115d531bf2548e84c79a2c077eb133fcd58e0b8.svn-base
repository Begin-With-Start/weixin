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
 * 聊天消息
 * @author ChenPengFei
 *
 */
@Entity
@Table(name = "Pmessage")
public class Pmessage {


	 public static final int READ = 1;//已读
	 public static final int UNREAD = 0;//未读
	 private Integer pmid;
     private XytUserInfo seuser;
     private XytUserInfo reuser;
     private String content;
     private Date createTime;
     private Integer sestatus;
     private Integer restatus;
     private Integer isread;
     private String ipaddress;
     private boolean my = false;
     private int unReadCount;


    @Id
 	@GeneratedValue(generator = "Pmessage_seq", strategy = GenerationType.SEQUENCE)
 	@SequenceGenerator(name = "Pmessage_seq", sequenceName = "s_pmessage")
    public Integer getPmid() {
        return this.pmid;
    }
    
    public void setPmid(Integer pmid) {
        this.pmid = pmid;
    }

    @Column(nullable = false)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    @Column(nullable = false)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(nullable = false)
    public Integer getIsread() {
        return this.isread;
    }
    
    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    @Column(nullable = false)
    public String getIpaddress() {
        return this.ipaddress;
    }
    
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public XytUserInfo getSeuser() {
		return seuser;
	}

	public void setSeuser(XytUserInfo seuser) {
		this.seuser = seuser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public XytUserInfo getReuser() {
		return reuser;
	}

	public void setReuser(XytUserInfo reuser) {
		this.reuser = reuser;
	}

	@Column(nullable = false)
	public Integer getSestatus() {
		return sestatus;
	}

	public void setSestatus(Integer sestatus) {
		this.sestatus = sestatus;
	}

	@Column(nullable = false)
	public Integer getRestatus() {
		return restatus;
	}

	public void setRestatus(Integer restatus) {
		this.restatus = restatus;
	}

	@Transient
	public boolean isMy() {
		return my;
	}

	public void setMy(boolean my) {
		this.my = my;
	}

	@Transient
	public int getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(int unReadCount) {
		this.unReadCount = unReadCount;
	}

	
}
