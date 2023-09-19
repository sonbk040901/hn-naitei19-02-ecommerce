package com.ecommerce.dto;

import com.ecommerce.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 23:46
 */
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDTO {
    private Integer page = 1;
    private Integer perPage = 3;

    public BaseDTO(BaseEntity entity) {
        fromEntity(entity);
    }

    public void fromEntity(BaseEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
