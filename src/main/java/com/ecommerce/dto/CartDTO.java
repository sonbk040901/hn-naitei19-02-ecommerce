package com.ecommerce.dto;

import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

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
public class CartDTO extends BaseDTO{
    @Valid
    List<CartDetailDTO> cartDetails;
}
