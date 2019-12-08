/**
 * 
 */
package com.zyt.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zyt.security.core.properties.SecurityProperties;

/**
 * @author Colin
 *
 */
//web应用安全配置的适配器
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//加密方法，可以用自己的加密方法（实现encode matches方法）
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//身份认证：表单登录 任何请求都需要身份认证
		http.formLogin()
			//设置自定义的登录页面路径
			.loginPage("/authentication/require")
			//设置登录路径
			.loginProcessingUrl("/authentication/form")
//		http.httpBasic()
			.and()
			.authorizeRequests()
			//signIn.html页面不进行拦截
			.antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll()
			.anyRequest()
			.authenticated()
			//暂时关闭csrf 跨站请求伪造防护功能
			.and().csrf().disable();
		
	}
}
