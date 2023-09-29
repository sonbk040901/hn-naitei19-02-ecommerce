package com.ecommerce.dto;

import com.ecommerce.model.CartDetail;

import lombok.*;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 19/09/2023
 * @Time: 15:19
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDTO extends BaseDTO {
    private Long cart_id;
    private Long product_id;
    private Integer quantity;

    public CartDetailDTO(CartDetail cartDetail) {
        super();
        this.cart_id = cartDetail.getCartId();
        this.product_id = cartDetail.getProductId();
        this.quantity = cartDetail.getQuantity();
    }
}
