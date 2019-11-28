/**
 * 
 */
package com.zyt.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Colin
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)		//运行时的注解
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
