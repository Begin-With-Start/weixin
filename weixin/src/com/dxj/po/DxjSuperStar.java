package com.dxj.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 大学伽校园网红
 */
@Entity
@Table(name = "DxjSuperStar")
public class DxjSuperStar {
	
	private Integer id; //id
	
	//姓名
	private String name;
	
	//学校
	private String schoolName;
	
	//性别
	private String sex;
	
	//年龄
	private int age;
	
	//电话号码
	private String telNumber;
	
	//照片路径
	private String photoPath;
	
	//点赞数
	private int likePointNumber = 0;
	
	//网红介绍
	private String introduction;
	
	//简介
	private String abstractIntroduction;

	@Id
	@GeneratedValue(generator = "DxjSuperStar_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "DxjSuperStar_seq", sequenceName = "s_DxjSuperStar")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = true)
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

	@Column(nullable = true)
	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	@Column(nullable = false)
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Column(nullable = false)
	public int getLikePointNumber() {
		return likePointNumber;
	}

	public void setLikePointNumber(int likePointNumber) {
		this.likePointNumber = likePointNumber;
	}

	@Column(nullable = false)
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@Column(nullable = true)
	public String getAbstractIntroduction() {
		return abstractIntroduction;
	}

	public void setAbstractIntroduction(String abstractIntroduction) {
		this.abstractIntroduction = abstractIntroduction;
	}
	
	
}
