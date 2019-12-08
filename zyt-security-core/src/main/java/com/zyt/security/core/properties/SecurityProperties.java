/**
 * 
 */
package com.zyt.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author Colin
 *
 */
//这个类 可以读取所有以zyt.security开头的配置项
@ConfigurationProperties(prefix = "zyt.security")
@Data
public class SecurityProperties {

	//注意：此处的变量名 browser 一定要与配置文件中zyt.security后面的那个字符串一致！
	private BrowserProperties browser = new BrowserProperties();
	
}
