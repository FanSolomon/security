/**
 * 
 */
package com.zyt.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Colin
 *
 */
//Filter是J2EE规范定义的，与spring框架无关
//@Component
public class TimeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("time filter init!");
	}

	//过滤器只能拿到http的请求和响应，不能得知当前请求调用的接口
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start!");
		long start = new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("time filter耗时:" + (new Date().getTime() - start));
		System.out.println("time filter finish!");
	}

	@Override
	public void destroy() {
		System.out.println("time filter destroy!");
	}

}
