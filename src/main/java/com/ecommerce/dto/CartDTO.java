package com.ecommerce.dto;

import java.util.List;

import com.ecommerce.model.Cart;

import groovy.transform.builder.Builder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 19/09/2023
 * @Time: 15:14
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO extends BaseDTO {
    @Valid
    private Long id;
    private List<CartDetailDTO> cartDetails;

    public CartDTO(Cart cart, List<CartDetailDTO> cartDetails) {
        this.cartDetails = cartDetails;
        this.id = cart.getId();
    }

    public CartDTO(Cart cart) {
        this.id = cart.getId();
    }
}
