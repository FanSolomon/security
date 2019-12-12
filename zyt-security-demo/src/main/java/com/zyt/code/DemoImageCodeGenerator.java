/**
 * 
 */
package com.zyt.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.zyt.security.core.validate.code.ValidateCode;
import com.zyt.security.core.validate.code.ValidateCodeGenerator;

/**
 * @author Colin
 *
 */
//@Component("imageValidateCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ValidateCode generate(ServletWebRequest request) {
		System.out.println("DEMO中实现的图形验证码生成逻辑！");
		return null;
	}

}
