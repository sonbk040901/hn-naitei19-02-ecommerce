package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.model.CartDetail;
import com.ecommerce.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @ResponseBody
    @PostMapping("/add")
    public void AddProductToCart(@RequestBody CartDetail cartDetail) throws Exception {
        cartService.addProductToCart(cartDetail.getCartId(), cartDetail.getProductId(), cartDetail.getQuantity());
        return;
    }
}