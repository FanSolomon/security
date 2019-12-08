/**
 * 
 */
package com.zyt.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Colin
 *
 */
//web应用安全配置的适配器
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//加密方法，可以用自己的加密方法（实现encode matches方法）
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//身份认证：表单登录 任何请求都需要身份认证
		http.formLogin()
//		http.httpBasic()
			.and().authorizeRequests().anyRequest().authenticated();
	}
}
