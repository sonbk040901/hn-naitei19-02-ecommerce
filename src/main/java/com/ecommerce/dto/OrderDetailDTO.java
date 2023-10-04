package com.ecommerce.dto;
import com.ecommerce.validator.ProductAvailable;
import lombok.*;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 16/09/2023
 * @Time: 00:52
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDTO extends BaseDTO {
    private Long price;
    private Long orderId;
    private ProductDTO product;
    private Long productId;
    private Integer quantity;
}
