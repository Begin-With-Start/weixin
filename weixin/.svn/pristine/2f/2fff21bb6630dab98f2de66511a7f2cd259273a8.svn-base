package com.xxcb.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Value;

import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	@Value("${basePath}")
	private String basePath;
	
	/**
	 * 获得session
	 * @return HttpSession
	 */
	public HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	/**
	 * 获得HttpServletRequest
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	/**
	 * 获得HttpServletResponse
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 去掉字符串空格
	 * @param str
	 * @return
	 */
	public String repBlank(String str){
		return str==null?"":str.replaceAll(" ", "");
	}
	
	public void showjsondata(String json){
		HttpServletResponse response =getResponse();
		response.setCharacterEncoding("utf-8");
		// 不要让浏览器开辟缓存
		 response.setHeader("Cache-Control","no-cache");
		 response.setHeader("Cache-Control","no-store");
		// 程序立即过期
		 response.setDateHeader("Expires",0);
		// 不要让浏览其缓存程序
		 response.setHeader("Pragma","no-cache");
		try {
		    response.getWriter().println(json);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
