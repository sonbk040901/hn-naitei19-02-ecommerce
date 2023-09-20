package com.ecommerce.dao;

import com.ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 23:12
 */
public interface OrderDAO extends DAO<Long, Order> {
    Page<Order> findAllByUserId(Long userId, Pageable pageable);
}
