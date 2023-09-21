package com.ecommerce.dto;

import com.ecommerce.model.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends BaseDTO {
    private Long id;
    private String name;
    private String description;
    private String thumbnail;
    private Long price;
    private Integer quantity;
    private Integer numberOfSale = 0;
    private Float rating;
    private Long categoryId;
    private List<ProductImageDTO> productImages;
}
