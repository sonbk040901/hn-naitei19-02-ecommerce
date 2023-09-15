package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total_price")
    private Integer totalPrice;
    @Column(name = "shipping_status")
    private String shippingStatus;
    @Column(name = "shipping_fee")
    private Long shippingFee;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", insertable=false, updatable=false)
    private Receiver receiver;

    @Column(name = "receiver_id")
    private Long receiverId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Account user;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    private Integer status;

    @OneToOne(mappedBy = "order")
    private RejectCause rejectCause;

    public Order(Integer totalPrice, String shippingStatus, Long shippingFee, Long receiverId, Long userId, Integer status) {
        this.totalPrice = totalPrice;
        this.shippingStatus = shippingStatus;
        this.shippingFee = shippingFee;
        this.receiverId = receiverId;
        this.userId = userId;
        this.status = status;
    }
}
