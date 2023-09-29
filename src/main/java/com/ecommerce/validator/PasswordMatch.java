package com.ecommerce.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 30/09/2023
 * @Time: 20:06
 */

@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface PasswordMatch {
    String message() default "Fields are not matching";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}