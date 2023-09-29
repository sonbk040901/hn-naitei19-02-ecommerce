package com.ecommerce.service;


import com.ecommerce.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 15:42
 */
public interface UserService extends Service<Long, UserDTO> {
    UserDTO findByUsername(String username);
    @Transactional
    UserDTO createUser(UserDTO userDTO);
}
