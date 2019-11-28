/**
 * 
 */
package com.zyt.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Colin
   *     校验的具体逻辑实现
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("My validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println("validator value is :"+value);
		return false;
	}
	
}
