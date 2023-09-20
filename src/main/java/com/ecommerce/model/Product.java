package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    private String name;

    private String description;

    private String thumbnail;

    private Long price;

    private Integer quantity;

    @Column(name = "number_of_sale")
    private Integer numberOfSale;

    private Float rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "category_id")
    private Long categoryId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImages;

    public Product(String description, String thumbnail, Long price, Integer quantity, Integer numberOfSale, Float rating, Long categoryId) {
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.quantity = quantity;
        this.numberOfSale = numberOfSale;
        this.rating = rating;
        this.categoryId = categoryId;
    }
}
