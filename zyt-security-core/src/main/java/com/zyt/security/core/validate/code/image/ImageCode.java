/**
 * 
 */
package com.zyt.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.zyt.security.core.validate.code.ValidateCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Colin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode extends ValidateCode {

	private BufferedImage image;
	
	public ImageCode(BufferedImage image, String code, int expireSec) {
		super(code, expireSec);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
}
