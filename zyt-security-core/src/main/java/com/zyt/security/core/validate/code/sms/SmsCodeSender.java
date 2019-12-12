/**
 * 
 */
package com.zyt.security.core.validate.code.sms;

/**
 * @author Colin
 *
 */
public interface SmsCodeSender {
	void send(String mobile, String code);
}
