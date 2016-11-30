/**
 * 校园通教师信息
 */

package com.xyt.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "XytTercherInfo")
public class XytTercherInfo {
	private Integer rid; //id
	
	//姓名
	private String name;
	
	//性别
	private String sex;
	
	//年龄
	private int age;
	
	//电话号码
	private String telNumber;
	
	//照片路径
	private String photoPath;
	
	//点赞数
	private int likePointNumber;
	
	//教师介绍
	private String teacherIntroduction;
		
	@Id
	@GeneratedValue(generator = "XytTercherInfo_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "XytTercherInfo_seq", sequenceName = "s_xytTercherInfo")
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
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(nullable = false)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(nullable = false)
	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	@Column(nullable = true)
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Column(nullable = true)
	public int getLikePointNumber() {
		return likePointNumber;
	}

	public void setLikePointNumber(int likePointNumber) {
		this.likePointNumber = likePointNumber;
	}

	@Column(nullable = true)
	public String getTeacherIntroduction() {
		return teacherIntroduction;
	}

	public void setTeacherIntroduction(String teacherIntroduction) {
		this.teacherIntroduction = teacherIntroduction;
	}

}
