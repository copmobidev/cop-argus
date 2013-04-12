package com.cop.mobi.rest.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author chris.liu
 * 
 */
public class CharacterEncodingFilter implements Filter {

	public static final String CHARSET_DEF = "UTF-8";

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 设置请求响应字符编码
		req.setCharacterEncoding(CHARSET_DEF);
		resp.setCharacterEncoding(CHARSET_DEF);
		// 新增加的代码
		HttpServletRequest httqReq = (HttpServletRequest) req;
		if (httqReq.getMethod().equalsIgnoreCase("get")) {
			httqReq = new MyHttpServletRequestWrapper(httqReq, CHARSET_DEF);
		}

		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {

	}

}
