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

import com.dxj.po.DxjSuperStar;

/**
 * 课程点赞信息保存
 * @author Administrator
 *
 */
@Entity
@Table(name = "XytLikePointForCourse")
public class XytLikePointForCourse {
	
	private Integer rid; //id
	
	private XytUserInfo xytUserInfo;
	
	private XytCourse xytCourse;
	
	private Date createDate;
	
	private DxjSuperStar dxjSuperStar;

	@Id
	@GeneratedValue(generator = "XytLikePointForCourse_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "XytLikePointForCourse_seq", sequenceName = "s_xytLikePointForCourse")
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	public XytUserInfo getXytUserInfo() {
		return xytUserInfo;
	}

	public void setXytUserInfo(XytUserInfo xytUserInfo) {
		this.xytUserInfo = xytUserInfo;
	}

	@JoinColumn(nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	public XytCourse getXytCourse() {
		return xytCourse;
	}

	public void setXytCourse(XytCourse xytCourse) {
		this.xytCourse = xytCourse;
	}

	@Column(nullable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JoinColumn(nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	public DxjSuperStar getDxjSuperStar() {
		return dxjSuperStar;
	}

	public void setDxjSuperStar(DxjSuperStar dxjSuperStar) {
		this.dxjSuperStar = dxjSuperStar;
	}
	
	
}
