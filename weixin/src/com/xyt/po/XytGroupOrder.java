package com.xyt.po;

import java.math.BigDecimal;
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

/**
 * 校园通组团订单信息
 */
@Entity
@Table(name = "XytGroupOrder")
public class XytGroupOrder {
	
	public enum expectTimeEnum {
        周一至周五中午班, 
        周一至周五晚上班, 
        周末班;
    }
	
	public enum groupIntentionEnum {
		纯个人,
		携同事及朋友组团,
		单独组团;
	}
	private Integer rid;
	
	//组团的名称（客户自定义）
	private String groupName;
	
	//团队的编码（自动生成）
	private String groupCode;
	
	//团队人数上限
	private Integer totalMemberNumber;
	
	//团队现有人数
	private Integer memberNumber;
	
	//拼团价
	private BigDecimal groupFee;
	
	//原价格(可)
	private BigDecimal originalFee;
	
	//课程
	private XytCourse xytCourse;
	
	//团长
	private XytUserInfo colonel;
	
	//创建时间
	private Date createDate;
	
	//报名参团截止时间
	private Date endDate;
	
	//期望上课地址
	private String expectAddress;
	
	//期望的授课老师名称
	private String expectTercherName;
	
	//授课老师信息
	private XytTercherInfo xytTercherInfo;
	
	//期望上课时间
	private expectTimeEnum expectTime;
	
	//报班的意向
	private groupIntentionEnum groupIntention;
	
	//是否开团
	private boolean isProceed;
	
	//团购价折扣
	private BigDecimal groupDiscount;
	
	//是否删除
	/**
	 * 0表示已删除，1表示未删除
	 */
	private boolean isRemove;

	@Id
	@GeneratedValue(generator = "XytGroupOrder_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "XytGroupOrder_seq", sequenceName = "s_xytGroupOrder")
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Column(nullable = true)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Column(nullable = false)
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	@Column(nullable = false)
	public Integer getTotalMemberNumber() {
		return totalMemberNumber;
	}

	public void setTotalMemberNumber(Integer totalMemberNumber) {
		this.totalMemberNumber = totalMemberNumber;
	}

	@Column(nullable = false)
	public Integer getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}

	@Column(nullable = false)
	public BigDecimal getGroupFee() {
		return groupFee;
	}

	public void setGroupFee(BigDecimal groupFee) {
		this.groupFee = groupFee;
	}

	@Column(nullable = false)
	public BigDecimal getOriginalFee() {
		return originalFee;
	}

	public void setOriginalFee(BigDecimal originalFee) {
		this.originalFee = originalFee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public XytCourse getXytCourse() {
		return xytCourse;
	}

	public void setXytCourse(XytCourse xytCourse) {
		this.xytCourse = xytCourse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public XytUserInfo getColonel() {
		return colonel;
	}

	public void setColonel(XytUserInfo colonel) {
		this.colonel = colonel;
	}

	@Column(nullable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(nullable = true)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(nullable = true)
	public String getExpectAddress() {
		return expectAddress;
	}

	public void setExpectAddress(String expectAddress) {
		this.expectAddress = expectAddress;
	}

	@Column(nullable = true)
	public String getExpectTercherName() {
		return expectTercherName;
	}

	public void setExpectTercherName(String expectTercherName) {
		this.expectTercherName = expectTercherName;
	}
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	public XytTercherInfo getXytTercherInfo() {
		return xytTercherInfo;
	}

	public void setXytTercherInfo(XytTercherInfo xytTercherInfo) {
		this.xytTercherInfo = xytTercherInfo;
	}

	@Column(nullable = true)
	public expectTimeEnum getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(expectTimeEnum expectTime) {
		this.expectTime = expectTime;
	}

	@Column(nullable = true)
	public groupIntentionEnum getGroupIntention() {
		return groupIntention;
	}

	public void setGroupIntention(groupIntentionEnum groupIntention) {
		this.groupIntention = groupIntention;
	}

	@Column(nullable = false)
	public boolean isProceed() {
		return isProceed;
	}

	public void setProceed(boolean isProceed) {
		this.isProceed = isProceed;
	}

	@Column(nullable = false)
	public BigDecimal getGroupDiscount() {
		return groupDiscount;
	}

	public void setGroupDiscount(BigDecimal groupDiscount) {
		this.groupDiscount = groupDiscount;
	}

	@Column(nullable = false)
	public boolean isRemove() {
		return isRemove;
	}

	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}
	
}
