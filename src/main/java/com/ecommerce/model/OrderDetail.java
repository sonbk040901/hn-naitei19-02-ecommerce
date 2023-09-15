package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable=false, updatable=false)
    private Order order;

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
    private Product product;

    @Column(name = "product_id")
    private Long productId;

    private Integer quantity;

    public OrderDetail(Long price, Long orderId, Long productId, Integer quantity) {
        this.price = price;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
