/**
 * 
 */
package com.zyt.security.core.properties;

import lombok.Data;

/**
 * @author Colin
 *
 */
@Data
public class ValidateCodeProperties {
	
	private ImageCodeProperties image = new ImageCodeProperties();
	
	private SmsCodeProperties sms = new SmsCodeProperties();
	
}
