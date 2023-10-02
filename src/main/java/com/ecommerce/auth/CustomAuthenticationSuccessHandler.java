package com.ecommerce.auth;

import com.ecommerce.model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 29/09/2023
 * @Time: 14:02
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> auhAuthorities = authentication.getAuthorities();
        for (var auhAuthority : auhAuthorities) {
            String role = auhAuthority.getAuthority();
            if (Account.Role.USER.name().equalsIgnoreCase(role)) {
                response.sendRedirect("/orders?login");
            }
        }
    }
}
