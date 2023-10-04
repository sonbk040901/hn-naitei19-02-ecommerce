package com.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.CartDetailDTO;
import com.ecommerce.exception.NotFound;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartDetail;
import com.ecommerce.service.CartService;
import com.ecommerce.userdetails.CustomUserDetails;

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
		if (cartOptional.isEmpty()) {
			return createCart(userId);
		}
		return getMappedCartDTO(cartOptional.get());
	}

	@Override
	@Transactional
	public Integer addProductToCart(Long productId, Integer quantity, Long userId) {
		Optional<Cart> cart = cartDAO.findByUserId(userId);
		if (cart.isEmpty()) {
			CartDTO cartDTO = createCart(userId);
			return createCartDetail(cartDTO.getId(), productId, quantity);
		}
		Optional<CartDetail> cartDetailOptional = cartDetailDAO.findByCartIdAndProductId(cart.get().getId(), productId);
		if (cartDetailOptional.isEmpty()) {
			return createCartDetail(cart.get().getId(), productId, quantity);
		}
		CartDetail cartDetail = cartDetailOptional.get();
		cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
		cartDetailDAO.save(cartDetail);
		return cartDetailDAO.countByCartId(cart.get().getId());
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
	public Integer deleteCartDetail(Long cartId, Long productId) {
		Optional<CartDetail> cartItem = cartDetailDAO.findByCartIdAndProductId(cartId, productId);
		if (cartItem.isEmpty()) {
			throw new NotFound("Product not found");
		}
		cartDetailDAO.delete(cartItem.get());
		return cartDetailDAO.countByCartId(cartId);
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

	@Override
	public Integer getCartSize(CustomUserDetails userDetails) {
		var user = userDetails.getUser();
		var cartOptional = cartDAO.findByUserId(user.getId());
		if (cartOptional.isPresent()) {
			var cart = cartOptional.get();
			return cartDetailDAO.countByCartId(cart.getId());
		}
		return 0;
	}

	@Override
	@Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
	public void deleteTimeoutProduct() {
		var cartDetails = cartDetailDAO.getCartDetailByCreatedAtTimeout(15);
		cartDetailDAO.deleteAll(cartDetails);
	}

	@Transactional
	private CartDTO createCart(Long userId) {
		var cart = new Cart(userId);
		cartDAO.save(cart);
		return new CartDTO(cart);
	}

	@Transactional
	private Integer createCartDetail(Long cartId, Long productId, Integer quantity) {
		var cartDetail = new CartDetail(quantity, cartId, productId);
		cartDetailDAO.save(cartDetail);
		return cartDetailDAO.countByCartId(cartId);
	}
}
