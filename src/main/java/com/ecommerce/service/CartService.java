package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.CartDetailDTO;
import com.ecommerce.userdetails.CustomUserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 26/09/2023
 * @Time: 02:09
 */
public interface CartService {

    CartDTO getCartByUserId(Long userId);

    CartDTO addProductToCart(Long cartId, Long productId, Integer quantity);

    boolean existsProductInCart(Long productId, Long userId);

    CartDTO getById(Long id);

    CartDTO getByUserId(Long userId);

    void updateQuantity(Long cartId, Long productId, Integer quantity);

    Long getTotalPrice(List<CartDetailDTO> cartDetails);

    boolean checkOwnerCart(Long cartId, Long userId);

    void deleteCartDetail(Long cartId, Long productId);

    int getCartSize(CustomUserDetails user);

    @Transactional
    void deleteTimeoutProduct();
}
