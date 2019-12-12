/**
 * 
 */
package com.zyt.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.zyt.security.core.properties.SecurityConstants;
import com.zyt.security.core.validate.code.ValidateCode;
import com.zyt.security.core.validate.code.impl.AbstractValidateCodeProcessor;

/**
 * @author Colin
 *
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		//getRequiredStringParameter表示请求中必须有paramName的参数
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		smsCodeSender.send(mobile, validateCode.getCode());
	}
}
