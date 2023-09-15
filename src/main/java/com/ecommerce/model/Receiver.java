package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "receivers")
public class Receiver extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
