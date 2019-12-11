/**
 * 
 */
package com.zyt.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Colin
 *
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
}
