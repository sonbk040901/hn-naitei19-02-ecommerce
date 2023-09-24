package com.ecommerce.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
    private String image;
    private Long productId;
}
