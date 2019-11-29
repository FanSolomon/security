/**
 * 
 */
package com.zyt.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Colin
 *
 */
//拦截器会拦截所有控制器，除了自己写的controller，spring提供的controller也会被拦截
@Component
public class Timeinterceptor implements HandlerInterceptor {

	//controller中某个方法被调用之前执行
	//返回值为false时，后面的方法将不会执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("handler Class:"+((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println("handler Method:"+((HandlerMethod)handler).getMethod().getName());
		System.out.println("preHandle end!");
		request.setAttribute("startTime", new Date().getTime());
		return true;
	}

	//controller中某个方法（未抛出异常）被调用之后执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle time interceptor耗时：" + (new Date().getTime() - (long)request.getAttribute("startTime")));
		System.out.println("postHandle end!");
	}

	//controller中某个方法被调用之后 postHandle()后执行
	//ControllerExceptionHandler(@ControllerAdvice)在afterCompletion之前
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion time interceptor耗时：" + (new Date().getTime() - (long)request.getAttribute("startTime")));
		System.out.println("ex is:"+ex);
		System.out.println("afterCompletion end!");
	}

}
