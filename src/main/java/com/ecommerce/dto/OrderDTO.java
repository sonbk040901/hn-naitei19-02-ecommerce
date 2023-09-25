package com.ecommerce.dto;

import jakarta.validation.Valid;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 16/09/2023
 * @Time: 00:02
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO extends BaseDTO {
    private Integer totalPrice;
    private String shippingStatus;
    private Long shippingFee;
    private Long receiverId;
    @Valid
    private ReceiverDTO receiver;
    private Long userId;
    private Integer status;
    @Valid
    private List<OrderDetailDTO> orderDetails;
    private ProductDTO firstProduct;
    private Date createdAt;
}
