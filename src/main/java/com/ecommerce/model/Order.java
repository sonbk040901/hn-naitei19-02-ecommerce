package com.ecommerce.model;

import com.ecommerce.dto.BaseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PENDING(0),
        ACCEPTED(1),
        REJECTED(2),
        CANCELLED(3),
        SHIPPING(4),
        COMPLETED(5);
        private final int value;
    }

    @Column(name = "total_price")
    private Long totalPrice;
    @Column(name = "shipping_status")
    private String shippingStatus;
    @Column(name = "shipping_fee")
    private Long shippingFee;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", insertable = false, updatable = false)
    private Receiver receiver;

    @Column(name = "receiver_id")
    private Long receiverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Account user;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "tinyint")
    @ColumnDefault("0")
    private Status status;

    @OneToOne(mappedBy = "order")
    private RejectCause rejectCause;

    public Order(BaseDTO dto) {
        super(dto);
    }

    public Order(Long totalPrice, String shippingStatus, Long shippingFee, Long receiverId, Long userId, Integer status) {
        this.totalPrice = totalPrice;
        this.shippingStatus = shippingStatus;
        this.shippingFee = shippingFee;
        this.receiverId = receiverId;
        this.userId = userId;
        this.status = Status.values()[status];
    }

    public Order(Long totalPrice, String shippingStatus, Long shippingFee, Long receiverId, Long userId, Status status) {
        this.totalPrice = totalPrice;
        this.shippingStatus = shippingStatus;
        this.shippingFee = shippingFee;
        this.receiverId = receiverId;
        this.userId = userId;
        this.status = status;
    }

    public Order(Long totalPrice, String shippingStatus, Long shippingFee, Receiver receiver, Long userId, List<OrderDetail> orderDetails) {
        this.totalPrice = totalPrice;
        this.shippingStatus = shippingStatus;
        this.shippingFee = shippingFee;
        this.receiver = receiver;
        this.userId = userId;
        this.orderDetails = orderDetails;
    }
    public int getStatusValue() {
        return status.value;
    }
}
