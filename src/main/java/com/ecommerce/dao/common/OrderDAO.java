package com.ecommerce.dao.common;

import com.ecommerce.dao.common.DAO;
import com.ecommerce.dao.custom.OrderDAOCustom;
import com.ecommerce.model.Order;
import org.springframework.stereotype.Repository;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 23:12
 */
//@Repository
public interface OrderDAO extends DAO<Long, Order>, OrderDAOCustom {
}
