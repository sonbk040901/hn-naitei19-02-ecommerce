package com.ecommerce.dao.common;

import com.ecommerce.dao.custom.ProductDAOCustom;
import com.ecommerce.model.Product;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 18/09/2023
 * @Time: 17:46
 */
public interface ProductDAO extends DAO<Long, Product>, ProductDAOCustom {
}
