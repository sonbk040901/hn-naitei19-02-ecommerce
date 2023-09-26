package com.ecommerce.validator;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.service.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 26/09/2023
 * @Time: 01:55
 */
@RequiredArgsConstructor
@Component
public class ProductAvailableValidator implements ConstraintValidator<ProductAvailable, OrderDTO> {
    private final ProductService productService;

    @Override
    public void initialize(ProductAvailable constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(OrderDTO orderDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<OrderDetailDTO> odds = orderDTO.getOrderDetails();
        return odds.stream().allMatch(
                odd -> productService.checkProductAvailable(
                        odd.getProductId(), odd.getQuantity()
                )
        );
    }
}
