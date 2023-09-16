package com.ecommerce.dao.common;

import com.ecommerce.dao.custom.CartDAOCustom;
import com.ecommerce.model.Cart;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 18/09/2023
 * @Time: 17:46
 */
public interface CartDAO extends DAO<Long, Cart>, CartDAOCustom {
}
