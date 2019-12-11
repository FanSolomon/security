/**
 * 
 */
package com.zyt.security.core.validate.code;

import java.time.LocalDateTime;

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
public class ValidateCode {

	private String code;
	
	private LocalDateTime expireTime;
	
	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
