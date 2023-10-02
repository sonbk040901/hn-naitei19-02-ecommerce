package com.ecommerce.dto;

import com.ecommerce.model.CartDetail;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Builder
public class CartDetailDTO extends BaseDTO {

    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;
    private ProductDTO product;

    public CartDetailDTO(CartDetail cartDetail) {
        super();
        this.quantity = cartDetail.getQuantity();
        this.id = cartDetail.getId();
        this.cartId = cartDetail.getCartId();
        this.productId = cartDetail.getProductId();
    }
}
