package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "reject_causes")
public class RejectCause extends BaseEntity {
    private String content;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @Column(name = "order_id")
    private Long orderId;

    public RejectCause(String content, Long orderId) {
        this.content = content;
        this.orderId = orderId;
    }
}
