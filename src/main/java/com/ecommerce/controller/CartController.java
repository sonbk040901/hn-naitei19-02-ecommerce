package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.UpdateCartDTO;
import com.ecommerce.exception.UpdateCartFail;
import com.ecommerce.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Integer addProductToCart(@RequestBody UpdateCartDTO addCartDTO) {
        var currentUser = getCurrentUser();
        Integer totalItem = cartService.addProductToCart(addCartDTO.getProductId(), addCartDTO.getQuantity(),
                currentUser.getId());
        return totalItem;
    }

    @GetMapping()
    public String cart(Model model) {
        var currentUser = getCurrentUser();
        CartDTO cart = cartService.getCartByUserId(currentUser.getId());
        Long totalPrice = cart.getCartDetails() != null ? cartService.getTotalPrice(cart.getCartDetails()) : 0L;
        model.addAttribute("cartItems", cart.getCartDetails());
        model.addAttribute("cartId", cart.getId());
        model.addAttribute("totalPrice", totalPrice);
        return "user/cart";
    }

    @PutMapping("/{cartId}/update/{productId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String updateCart(@RequestBody UpdateCartDTO updateCartDTO, @PathVariable Long cartId,
            @PathVariable Long productId) {
        var currentUser = getCurrentUser();
        if (!cartService.checkOwnerCart(cartId, currentUser.getId())) {
            throw new UpdateCartFail("You are not owner of this cart");
        }
        cartService.updateQuantity(cartId, productId, updateCartDTO.getQuantity());
        return "Cart updated successfully";
    }

    @DeleteMapping("/{cartId}/delete/{productId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Integer deleteProductInCart(@PathVariable Long cartId, @PathVariable Long productId) {
        var currentUser = getCurrentUser();
        if (!cartService.checkOwnerCart(cartId, currentUser.getId())) {
            throw new UpdateCartFail("You are not owner of this cart");
        }
        Integer totalItem = cartService.deleteCartDetail(cartId, productId);
        return totalItem;
    }


}
