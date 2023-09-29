package com.ecommerce.controller;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import com.ecommerce.dto.CartDTO;
import com.ecommerce.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public String showProductDetail(@PathVariable long id, Model model) {
        ProductDTO product = productService.get(id);
        model.addAttribute("product", product);

        CartDTO cart = cartService.getCartByUserId((long) 16);
        model.addAttribute("cart", cart);
        model.addAttribute("num_of_products", cart.getCartDetails().size());
        return "user/product/show";
    }
}
