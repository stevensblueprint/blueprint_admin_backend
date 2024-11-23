package com.sitblueprint.admin.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailDomainValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailDomain {
	String message() default "Domain must be stevens.edu";
	String domain() default "stevens.edu";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
