package com.ecommerce.service.impl;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.model.Cart;
import com.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 26/09/2023
 * @Time: 02:10
 */
@Service
public class CartServiceImpl extends BaseService implements CartService {
    @Override
    public List<CartDTO> get() {
        return null;
    }

    @Override
    public CartDTO get(Long id) {
        return null;
    }

    @Override
    public void save(CartDTO e) {

    }

    @Override
    public void update(CartDTO e) {

    }

    @Override
    public void delete(CartDTO e) {

    }

    @Override
    public boolean existsProductInCart(Long productId, Long userId) {
        Optional<Cart> cartOptional = cartDAO.findByUserId(userId);
        if (cartOptional.isEmpty()) {
            return false;
        }
        Cart cart = cartOptional.get();
        return cart.getCartDetails().stream().anyMatch(cd -> cd.getProductId().equals(productId));
    }
}
