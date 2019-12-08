/**
 * 
 */
package com.zyt.security.browser.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Colin
 *
 */
@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor 
public class SimpleResponse {

	private Object content;
}
