package com.ecommerce.dto;

import com.ecommerce.model.OrderDetail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Negative;
import lombok.*;

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
    private Long id;
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
}
