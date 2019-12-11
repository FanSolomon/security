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
public class SmsCodeProperties {

	private int length = 6;
	
	private int expireIn = 60;
	
	private String url;
	
}
