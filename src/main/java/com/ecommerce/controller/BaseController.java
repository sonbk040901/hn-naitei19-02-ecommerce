package com.ecommerce.controller;

import com.ecommerce.model.Account;
import com.ecommerce.userdetails.CustomUserDetails;
import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 29/09/2023
 * @Time: 18:18
 */
public abstract class BaseController {
    public static Account getCurrentUser() {
        var userDetails = getCurrentUserDetails();
        return userDetails.getUser();
    }

    public static CustomUserDetails getCurrentUserDetails() {
        Authentication authentication = getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
