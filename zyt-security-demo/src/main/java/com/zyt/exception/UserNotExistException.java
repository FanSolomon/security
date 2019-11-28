/**
 * 
 */
package com.zyt.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Colin
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3438677113716937305L;

	private String id;
	
	public UserNotExistException(String id) {
		super("user not exist!");
		this.id = id;
	}
}
