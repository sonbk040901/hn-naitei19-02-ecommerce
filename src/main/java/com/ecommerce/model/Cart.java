package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Account user;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartDetail> cartDetails;

    public Cart(Long userId) {
        this.userId = userId;
    }
}
