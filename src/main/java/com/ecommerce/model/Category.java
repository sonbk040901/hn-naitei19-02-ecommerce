package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Category parent;

    @Column(name = "parent_id")
    private Long parentId;

    public Category(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
