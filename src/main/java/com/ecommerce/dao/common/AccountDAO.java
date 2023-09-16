package com.ecommerce.dao.common;

import com.ecommerce.dao.common.DAO;
import com.ecommerce.dao.custom.AccountDAOCustom;
import com.ecommerce.model.Account;
import org.springframework.stereotype.Repository;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 15:44
 */


public interface AccountDAO extends DAO<Long, Account>, AccountDAOCustom {
}
