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
	
	//设置length的默认值为4 而不是SmsCodeProperties中的6
	public ImageCodeProperties() {
		setLength(4);
	}

}
