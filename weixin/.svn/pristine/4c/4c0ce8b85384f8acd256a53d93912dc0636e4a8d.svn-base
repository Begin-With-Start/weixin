package com.xxcb.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 统一编码公共类
 * @author zhangpeng
 *
 */
public class EncodingFilter implements Filter {

	private String targetEncoding = "UTF-8";

	public void init(FilterConfig config) throws ServletException {
		targetEncoding = config.getInitParameter("encoding");
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) srequest;
		request.setCharacterEncoding(targetEncoding);
		chain.doFilter(srequest, sresponse);
	}
}
