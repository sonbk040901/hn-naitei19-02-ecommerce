package com.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.ecommerce.model.Cart;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 18/09/2023
 * @Time: 17:46
 */
public interface CartDAO extends DAO<Long, Cart> {
    Optional<Cart> findByUserId(Long userId);

    Optional<Cart> findById(Long id);

    @Modifying
    @Query("DELETE FROM CartDetail cd WHERE cd.cartId = ?1")
    void emptyCart(Long cartId);

}
