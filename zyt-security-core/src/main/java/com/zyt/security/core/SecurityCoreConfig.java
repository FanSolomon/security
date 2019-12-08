/**
 * 
 */
package com.zyt.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.zyt.security.core.properties.SecurityProperties;

/**
 * @author Colin
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
