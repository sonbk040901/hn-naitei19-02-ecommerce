package com.ecommerce.dao.impl;

import com.ecommerce.dao.custom.AccountDAOCustom;
import com.ecommerce.model.Account;
import org.springframework.stereotype.Repository;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 16:15
 */
@Repository
public class AccountDAOImpl extends BaseDAO implements AccountDAOCustom {
    @Override
    public Account findByUsername(String username) {
        var a =  session.createQuery("FROM Account WHERE username = :username", Account.class)
                .setParameter("username", username).list().get(0);
        return a;
    }
}