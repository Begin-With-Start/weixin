package com.xxcb.po;

import java.util.List;

/**
 * 关注用户列表
 * @author lidu
 * @date 2015.7.13
 */
public class UserList {
	//关注该公众账号的总用户数
	private int total;
	
	//拉取的OPENID个数，最大值为10000
	private int count;
	
	//列表数据，OPENID的列表
	private List<String> data;
	
	//拉取列表的最后一个用户的OPENID
	private String next_openid;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
}
