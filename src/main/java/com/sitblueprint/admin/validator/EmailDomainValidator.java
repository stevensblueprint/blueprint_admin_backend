package com.sitblueprint.admin.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<EmailDomain, String> {

	private String domain;

	@Override
	public void initialize(EmailDomain constraintAnnotation) {
		this.domain = constraintAnnotation.domain();
	}
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		if (s == null || s.isEmpty()) {
			return false;
		}
		return s.endsWith("@" + domain);
	}
}
