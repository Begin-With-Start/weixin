package com.xxcb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 关键字与素材的映射关系
 * @author Administrator
 *
 */

@Entity
@Table(name = "wx_keymaterialmap")
public class KeyMaterialMap {
	@Id
	@GeneratedValue(generator = "KeyMaterialMap_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "KeyMaterialMap_seq", sequenceName = "S_WX_KEYMATERIALMAP")
	@Column(name = "RID")
	private Integer rid; //id
	
	private String keyword; //关键字

	private String mediaId;//媒体id
	
	private String title;//标题
	
	private String picurl;//图片链接
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

}
