package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartDTO {
    private Integer quantity;

    public UpdateCartDTO() {
    }

    public UpdateCartDTO(Integer quantity) {
        this.quantity = quantity;
    }
}
