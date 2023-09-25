package com.ecommerce.dao;

import com.ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 23:12
 */
public interface OrderDAO extends DAO<Long, Order> {
    @Query("SELECT o FROM Order o WHERE o.userId = :userId and (o.status = :status or :status = null) and ((:from=null and :to=null ) or (:from=null and o.createdAt <= :to) or (:to=null and o.createdAt >= :from) or (o.createdAt between :from and :to))")
    Page<Order> findAllByUserIdAndCreatedAtBetweenAndStatusLike(Long userId, Date from, Date to, Order.Status status, Pageable pageable);
}
