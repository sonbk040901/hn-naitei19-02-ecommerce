package com.ecommerce.service.impl;

import com.ecommerce.dao.AccountDAO;
import com.ecommerce.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 29/09/2023
 * @Time: 13:43
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl extends BaseService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional = accountDAO.findFirstByUsername(username);
        var user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
