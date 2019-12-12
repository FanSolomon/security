/**
 * 
 */
package com.zyt.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.zyt.security.core.properties.SecurityProperties;
import com.zyt.security.core.validate.code.ValidateCodeFilter;
import com.zyt.security.core.validate.code.ValidateCodeSecurityConfig;

/**
 * @author Colin
 *
 */
//web应用安全配置的适配器
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler myAuthenticationFailureHandler;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//加密方法，可以用自己的加密方法（实现encode matches方法）
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		//配置数据源
		tokenRepository.setDataSource(dataSource);
		//第一次时 配置此项让系统自动创建表
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//		validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
//		validateCodeFilter.setSecurityProperties(securityProperties);
//		validateCodeFilter.afterPropertiesSet();
		
		http
			.apply(validateCodeSecurityConfig).and()
			//将validateCodeFilter加在UsernamePasswordAuthenticationFilter之前
//			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			//身份认证：表单登录 任何请求都需要身份认证
			.formLogin()
				//设置自定义的登录页面路径
				.loginPage("/authentication/require")
				//设置登录路径
				.loginProcessingUrl("/authentication/form")
				//指定自定义的成功登录后处理方式
				.successHandler(myAuthenticationSuccessHandler)
				//指定自定义的失败登录后处理方式
				.failureHandler(myAuthenticationFailureHandler)
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
//		http.httpBasic()
			.and()
			.authorizeRequests()
			//signIn.html页面不进行拦截
			.antMatchers("/authentication/require", 
					securityProperties.getBrowser().getLoginPage(), 
					"/code/*").permitAll()
			.anyRequest()
			.authenticated()
			//暂时关闭csrf 跨站请求伪造防护功能
			.and().csrf().disable();
		
	}
}
