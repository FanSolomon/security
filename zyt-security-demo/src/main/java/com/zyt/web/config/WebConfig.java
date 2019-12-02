/**
 * 
 */
package com.zyt.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zyt.web.filter.TimeFilter;
import com.zyt.web.interceptor.Timeinterceptor;

/**
 * @author Colin
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private Timeinterceptor timeinterceptor;
	
	//配置拦截器 (异步情况使用)
//	@Override
//	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//		configurer.registerCallableInterceptors(interceptors)
//		super.configureAsyncSupport(configurer);
//	}

	//配置拦截器 (同步情况使用)
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeinterceptor);
//		super.addInterceptors(registry);
	}

	//在应用的第三方filter时，第三方没有@Component，则可用这种方式配置filter
//	@Bean
	public FilterRegistrationBean timeFilter() {
		//相当于在web.xml文件中去配置filter标签
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		
		//可配置 指定那些url使用filter
		List<String> urls = new ArrayList<String>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		
		System.out.println("public FilterRegistrationBean timeFilter end!");
		return registrationBean;
	}
}
