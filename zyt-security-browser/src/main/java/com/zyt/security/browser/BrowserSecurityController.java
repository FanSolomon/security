/**
 * 
 */
package com.zyt.security.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zyt.security.browser.support.SimpleResponse;
import com.zyt.security.core.properties.SecurityProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Colin
 *
 */
@Slf4j
@RestController
public class BrowserSecurityController {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	//spring security会将当前的请求缓存到session中（HttpSessionRequestCache）
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/**
	 * 当需要身份认证时，跳转到这里
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (null != savedRequest) {
			String targetUrl = savedRequest.getRedirectUrl();
			log.info("当前的请求：{}", targetUrl);
			if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				String pageUrl = securityProperties.getBrowser().getLoginPage();
				log.info("------getLoginPage is: {}", pageUrl);
				log.info("request.getContextPath() is: {}",request.getContextPath());
				redirectStrategy.sendRedirect(request, response, pageUrl);
			}
		}
		return new SimpleResponse("访问的服务需要身份认证");
	}
}
