package com.ecommerce.model;

import com.ecommerce.dto.BaseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.BeanUtils;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 08:58
 * @apiNote : This is abstract class for all entity
 */
@Data
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    public BaseEntity(BaseDTO dto) {
        fromDTO(dto);
    }

    public void fromDTO(BaseDTO dto) {
        BeanUtils.copyProperties(dto, this);
    }
}
