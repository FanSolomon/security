/**
 * 
 */
package com.zyt.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Colin
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1699776914653308825L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
