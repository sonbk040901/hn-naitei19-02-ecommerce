package com.ecommerce.service.impl;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.Account;
import com.ecommerce.model.Order;
import com.ecommerce.service.UserService;
import com.ecommerce.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 16:10
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> get() {
        return null;
    }

    @Override
    public UserDTO get(Long id) {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {
    }

    @Override
    public void update(UserDTO userDTO) {

    }

    @Override
    public void delete(UserDTO userDTO) {

    }

    @Override
    public UserDTO findByUsername(String username) {
        var u = accountDAO.findFirstByUsername(username);
        return modelMapper.map(u.orElseThrow(() -> new UsernameNotFoundException("User not found")), UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Account account = modelMapper.map(userDTO, Account.class);
        account.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        accountDAO.save(account);
        return modelMapper.typeMap(Account.class, UserDTO.class).addMappings(
                modelMapper1 -> {
                    modelMapper1.skip(UserDTO::setPassword);
                    modelMapper1.map(Account::getGenderValue, UserDTO::setGender);
                    modelMapper1.map(Account::getRoleValue, UserDTO::setRole);
                }
        ).map(account);
    }
}
