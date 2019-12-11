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
public class ImageCodeProperties extends SmsCodeProperties {
	
	private int width = 67;
	
	private int height = 23;
	
	public ImageCodeProperties() {
		setLength(4);
	}

}
