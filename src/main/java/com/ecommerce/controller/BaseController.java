package com.ecommerce.controller;

import com.ecommerce.model.Account;
import com.ecommerce.userdetails.CustomUserDetails;
import lombok.NonNull;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 29/09/2023
 * @Time: 18:18
 */
public abstract class BaseController {
    public static Account getCurrentUser() {
        var currentUserDetails = getCurrentUserDetails();
        return currentUserDetails.getUser();
    }

    public static CustomUserDetails getCurrentUserDetails() {
        var authentication = getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }

    private static Authentication getAuthentication() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(authentication, "No authentication found in context");
        return authentication;
    }

}
