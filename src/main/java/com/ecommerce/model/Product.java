package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String description;

    private String thumbnail;

    private Integer price;

    private Integer quantity;

    @Column(name = "number_of_sale")
    private Integer numberOfSale;

    private Float rating;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "category_id")
    private Long categoryId;

    public Product(String description, String thumbnail, Integer price, Integer quantity, Integer numberOfSale, Float rating, Long categoryId) {
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.quantity = quantity;
        this.numberOfSale = numberOfSale;
        this.rating = rating;
        this.categoryId = categoryId;
    }
}
