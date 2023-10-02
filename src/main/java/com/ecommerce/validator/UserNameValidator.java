package com.ecommerce.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 29/09/2023
 * @Time: 15:45
 */
@Component
public class UserNameValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Username is required.");
    }
}
