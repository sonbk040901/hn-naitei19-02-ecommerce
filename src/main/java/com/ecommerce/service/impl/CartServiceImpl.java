package com.ecommerce.service.impl;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.model.Cart;
import com.ecommerce.dto.CartDetailDTO;
import com.ecommerce.exception.NotFound;
import com.ecommerce.model.CartDetail;
import com.ecommerce.service.CartService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
	@Transactional
	public CartDTO getCartByUserId(Long userId) {
		Optional<Cart> cartOptional = cartDAO.findByUserId(userId);

		Cart cart;
		// Kiểm tra xem cart có tồn tại ko, nếu không thì tạo mới
		boolean isEmpty = cartOptional.isEmpty();
		if (isEmpty) {
			cart = new Cart(userId);
			cartDAO.save(cart);
			throw new NotFound("Cart is empty");
		}
		// Nếu có thì lấy cart đó ra
		cart = cartOptional.get();
		List<CartDetail> cartDetails = cart.getCartDetails();

		List<CartDetailDTO> cartDetailDTOs = new ArrayList<CartDetailDTO>();

		for (int i = 0; i < cartDetails.size(); i++) {
			CartDetail detail = cartDetails.get(i);
			cartDetailDTOs.add(i, new CartDetailDTO(detail));
		}

		CartDTO cartDTO = new CartDTO(cart, cartDetailDTOs);
		return cartDTO;
	}

	@Override
	public CartDTO addProductToCart(Long cartId, Long productId, Integer quantity) {
		Optional<Cart> cartOptional = cartDAO.findById(cartId);

		Cart cart;
		cart = cartOptional.get();

		CartDetail cartDetail = new CartDetail(quantity, cartId, productId);
		cartDetailDAO.save(cartDetail);
		cart.getCartDetails().add(cartDetail);
		cart.setCartDetails(cart.getCartDetails());
		List<CartDetail> cartDetails = cart.getCartDetails();

		List<CartDetailDTO> cartDetailDTOs = new ArrayList<CartDetailDTO>();

		for (int i = 0; i < cartDetails.size(); i++) {
			CartDetail detail = cartDetails.iterator().next();
			cartDetailDTOs.add(i, new CartDetailDTO(detail));
		}
		cartDAO.save(cart);
		return new CartDTO(cart, cartDetailDTOs);
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
