package com.xxcb.util;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 分页
 * @author zhangpeng
 *
 */
@SuppressWarnings("unchecked")
@Component
@Scope("prototype")
public class PagerUtil {

    private int pageNo = 1;;

    private int pageSize = 5;

    private int totalCount;//总记录数

    private int totalPageCount;//总页数

  
	private List list;

    public int getPageSize() {
	return pageSize;
    }

    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    public int getPageNo() {
	
	return pageNo;
    }

    public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
    }

    public List getList() {
	return list;
    }

    public void setList(List list) {
	this.list = list;
    }

    public int getTotalCount() {
	return totalCount;
    }

    public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
	totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
		: totalCount / pageSize + 1;
	return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
	this.totalPageCount = totalPageCount;
    }

}
