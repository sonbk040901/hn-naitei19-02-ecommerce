package com.ecommerce.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 29/09/2023
 * @Time: 15:57
 */
@Component
public class CustomUserDetailsChecker  implements UserDetailsChecker {


    @Override
    public void check(UserDetails toCheck) {
        if(toCheck.getUsername() == null) {
            throw new IllegalArgumentException("Username cannot be null.");
        }
    }
}
