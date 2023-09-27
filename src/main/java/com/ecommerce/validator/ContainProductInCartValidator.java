package com.ecommerce.validator;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.service.CartService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 26/09/2023
 * @Time: 02:07
 */
@RequiredArgsConstructor
@Component
public class ContainProductInCartValidator implements ConstraintValidator<ContainProductInCart, OrderDTO> {
    private final CartService cartService;

    @Override
    public void initialize(ContainProductInCart constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(OrderDTO orderDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<OrderDetailDTO> odds = orderDTO.getOrderDetails();
        return odds.stream().allMatch(odd -> cartService.existsProductInCart(odd.getProductId(), orderDTO.getUserId()));
    }
}
