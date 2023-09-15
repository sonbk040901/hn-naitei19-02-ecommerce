package com.ecommerce.dao.impl;

import com.ecommerce.dao.AccountDAO;
import com.ecommerce.model.Account;
import org.springframework.stereotype.Repository;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 16:15
 */
@Repository
public class AccountDAOImpl extends BaseDAO<Long, Account> implements AccountDAO {
    public AccountDAOImpl() {
        super(Account.class);
    }
}
