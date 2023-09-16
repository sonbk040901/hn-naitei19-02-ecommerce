package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "receivers")
public class Receiver extends BaseEntity{
    private String name;
    @Column(columnDefinition = "char(10)")
    private String phone;
    private String address;

    public Receiver(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
