package com.ecommerce.dto;

import com.ecommerce.model.ProductImage;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO extends BaseDTO {
    private Long id;
    private String name;
    private String description;
    private String thumbnail;
    private Long price;
    private Integer quantity;
    @Builder.Default
    private Integer numberOfSale = 0;
    private Float rating;
    private Long categoryId;
    private List<ProductImageDTO> productImages;
}
