package com.ecommerce.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.model.CartDetail;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 20/09/2023
 * @Time: 00:16
 */
public interface CartDetailDAO extends DAO<Long, CartDetail> {
    void deleteAllByCartId(Long cartId);

    List<CartDetail> findByCartId(Long cart_id);

    @Query(value = "UPDATE cart_details SET quantity = ?3 WHERE cart_id = ?1 AND product_id = ?2", nativeQuery = true)
    void updateQuantity(Long id, Long productId, Integer quantity);

    void deleteByProductId(Long productId);


    @Modifying
    @Query(value = "SELECT * FROM cart_details cd WHERE cd.created_at < SUBDATE(CURRENT_TIMESTAMP, INTERVAL ?1 DAY)", nativeQuery = true)
    List<CartDetail> getCartDetailByCreatedAtTimeout(int exp);

    @Query(value = "SELECT * FROM cart_details WHERE cart_id = ?1 AND product_id = ?2 limit 1", nativeQuery = true)
    Optional<CartDetail> findByCartIdAndProductId(Long cartId, Long productId);

    long countByCartId(Long cartId);
}
