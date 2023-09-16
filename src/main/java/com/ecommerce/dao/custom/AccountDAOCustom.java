package com.ecommerce.dao.custom;

import com.ecommerce.model.Account;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 18/09/2023
 * @Time: 17:21
 */
public interface AccountDAOCustom {
    Account findByUsername(String username);
}
