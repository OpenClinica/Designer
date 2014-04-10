package org.akaza.openclinica.designer.web.controller;

import org.openclinica.ns.rules.v31.TargetType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class TargetTypeValidator implements Validator {

	public boolean supports(Class clazz) {
		return TargetType.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		TargetType p = (TargetType) obj;
		if (p.getValue().equals("")) {
			e.rejectValue("value", "empty");
		}
	}
}
