package com.ecommerce.validator;

import com.ecommerce.dto.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.val;
import org.springframework.beans.BeanUtils;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 30/09/2023
 * @Time: 20:07
 */

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserDTO> {
    public void initialize(final PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    public boolean isValid(final UserDTO value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            val firstObj = value.getPassword();
            val secondObj = value.getConfirmPassword();

            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {
            // ignore
        }
        if (!valid) {
            context.buildConstraintViolationWithTemplate("Mật khẩu và xác nhận phải giống nhau").addPropertyNode("password")
                    .addConstraintViolation().disableDefaultConstraintViolation();
        }
        return valid;
    }
}