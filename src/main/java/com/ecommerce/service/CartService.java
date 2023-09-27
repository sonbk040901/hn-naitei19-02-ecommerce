package com.ecommerce.service;

import com.ecommerce.dto.CartDTO;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 26/09/2023
 * @Time: 02:09
 */
public interface CartService extends Service<Long, CartDTO> {
    boolean existsProductInCart(Long productId, Long userId);
}
