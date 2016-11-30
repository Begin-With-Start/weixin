package com.xxcb.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 保存用户订单信息
 * @author lidu
 * @date 2015.9.10
 */
@Entity
@Table(name = "WX_ORDER")
public class Order {
	@Id
	@GeneratedValue(generator = "Order_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "Order_seq", sequenceName = "S_WX_Order")
	@Column(name = "RID")
	private Integer rid; //id
	
	private String name;
	
	private String tel;
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String addr;
	
	private String amount;
	
	private String product;
	
	private String remark;
}
