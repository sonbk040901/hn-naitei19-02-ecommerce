package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private Integer price;
    private Integer quantity;
    private Integer numberOfSale;
    private Float rating;
}
