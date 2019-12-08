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
public class BrowserProperties {

	//指定默认值 此处的变量名 loginPage 也一定要与配置文件中zyt.security.browser后面的那个字符串一致！
	private String loginPage = "/signIn.html";
	
}
