package com.ecommerce.service.impl;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.Account;
import com.ecommerce.service.UserService;
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
public class UserServiceImpl extends BaseService implements UserService {
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
        accountDAO.save(new Account(userDTO));
    }

    @Override
    public void update(UserDTO userDTO) {

    }

    @Override
    public void delete(UserDTO userDTO) {

    }

    @Override
    @Transactional
    public UserDTO findByUsername(String username) {
        var u = accountDAO.findAllByUsername(username);
        if (u.isEmpty())
            throw new RuntimeException("User not found");
        return new UserDTO(u.get());
    }
}
