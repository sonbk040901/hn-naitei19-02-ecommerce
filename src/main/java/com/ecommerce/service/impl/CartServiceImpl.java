package com.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.CartDetailDTO;
import com.ecommerce.exception.NotFound;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartDetail;
import com.ecommerce.service.CartService;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 26/09/2023
 * @Time: 02:10
 */
@Service
public class CartServiceImpl extends BaseService implements CartService {

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
			return new CartDTO(cart);
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

	@Override
	public boolean checkOwnerCart(Long cartId, Long userId) {
		Optional<Cart> cartOptional = cartDAO.findById(cartId);
		if (cartOptional.isEmpty()) {
			return false;
		}
		Cart cart = cartOptional.get();
		return cart.getUserId().equals(userId);
	}

	@Override
	@Transactional
	public void updateQuantity(Long cartId, Long productId, Integer quantity) {
		Optional<CartDetail> cartDetailOptional = cartDetailDAO.findByCartIdAndProductId(cartId, productId);
		if (cartDetailOptional.isEmpty()) {
			throw new NotFound("Product not found");
		}

		CartDetail cartDetail = cartDetailOptional.get();
		if (cartDetail.getQuantity() <= 0) {
			cartDetailDAO.delete(cartDetail);
		} else {
			cartDetail.setQuantity(quantity);
			cartDetailDAO.save(cartDetail);
		}
	}

	@Override
	@Transactional
	public void deleteCartDetail(Long cartId, Long productId) {
		Optional<CartDetail> cartItem = cartDetailDAO.findByCartIdAndProductId(cartId, productId);
		if (cartItem.isEmpty()) {
			throw new NotFound("Product not found");
		}
		cartDetailDAO.delete(cartItem.get());
	}

	@Override
	public CartDTO getById(Long id) {
		return getMappedCartDTO(cartDAO.findById(id).get());
	}

	@Override
	public CartDTO getByUserId(Long userId) {
		return getMappedCartDTO(cartDAO.findByUserId(userId).get());
	}

	@Override
	public Long getTotalPrice(List<CartDetailDTO> cartDetails) {
		Long totalPrice = 0L;
		for (CartDetailDTO cartDetail : cartDetails) {
			totalPrice += cartDetail.getQuantity() * (cartDetail.getProduct().getPrice());
		}
		return totalPrice;
	}

	private CartDTO getMappedCartDTO(Cart cart) {
		return modelMapper
				.typeMap(Cart.class, CartDTO.class)
				.map(cart);
	}

}
