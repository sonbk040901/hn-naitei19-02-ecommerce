package com.ecommerce.service.impl;

import com.ecommerce.model.Account;
import com.ecommerce.service.UserService;
import org.springframework.stereotype.Service;

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
    public List<Account> get() {
        return null;
    }

    @Override
    public Account get(Long id) {
        return null;
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Account account) {

    }
}
