package com.ecommerce.dto;

import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

import com.ecommerce.model.Cart;

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
    public CartDTO(Cart cart, List<CartDetailDTO> cartDetails) {
        this.cartDetails = cartDetails;
        this.user_id = cart.getUserId();
        this.id = cart.getId();
    }

    private Long id;
    private Long user_id;
    @Valid
    List<CartDetailDTO> cartDetails;
}
