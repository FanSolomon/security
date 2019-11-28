/**
 * 
 */
package com.zyt.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.zyt.validator.MyConstraint;

import lombok.Data;

/**
 * @author Colin
 *
 */
@Data
public class User {
	
	private String id;

	@MyConstraint(message = "测试 @MyConstraint")
	private String username;
	
	@NotBlank(message = "密码不能为空")
	private String password;
	
	@Past
	private Date birthday;
}
