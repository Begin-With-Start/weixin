package com.xyt.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 校园通学校群组信息
 */
@Entity
@Table(name = "XytCollege")
public class XytCollege {

	private Integer rid; //id
	
	//学校名字
	private String name;
	
	//群组总人数
	private Integer groupNumber;

	//经度
	private Float longitude;

	//纬度
	private Float latitude;

	//照片路径
	private String photoPath;

	//学校简介
	private String schollIntroduction;

	//学校地址
	private String address;

	//在校生总人数
	private Integer totalNumber;
	
	@Id
	@GeneratedValue(generator = "XytCollege_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "XytCollege_seq", sequenceName = "s_xytCollege")
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public Integer getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
	}

	@Column(nullable = false)
	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Column(nullable = false)
	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	@Column(nullable = false)
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Column(nullable = false)
	public String getSchollIntroduction() {
		return schollIntroduction;
	}

	public void setSchollIntroduction(String schollIntroduction) {
		this.schollIntroduction = schollIntroduction;
	}

	@Column(nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(nullable = false)
	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
}
