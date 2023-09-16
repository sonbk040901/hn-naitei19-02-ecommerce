package com.ecommerce.dto;

import jakarta.validation.constraints.Negative;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 16/09/2023
 * @Time: 00:52
 */
@Getter
@Setter
@ToString
public class OrderDetailDTO extends BaseDTO {
    private Long id;
    private Long price;
    private Long orderId;
    private Long productId;
    private Integer quantity;
}
