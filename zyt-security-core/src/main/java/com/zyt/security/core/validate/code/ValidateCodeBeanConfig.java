/**
 * 
 */
package com.zyt.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.zyt.security.core.properties.SecurityProperties;
import com.zyt.security.core.validate.code.image.ImageCodeGenerator;
import com.zyt.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.zyt.security.core.validate.code.sms.SmsCodeSender;

/**
 * @author Colin
 *
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * spring容器中不存在名为imageValidateCodeGenerator的been时 初始化ValidateCodeGenerator这个been
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator(); 
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	/**
	 * (SmsCodeSender.class)与上面name = 形式的效果一样
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}
