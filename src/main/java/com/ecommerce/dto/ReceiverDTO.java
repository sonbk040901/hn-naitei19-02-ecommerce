package com.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 16/09/2023
 * @Time: 00:21
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverDTO extends BaseDTO {
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @NotEmpty(message = "Name must not be empty")
    private String name;
    @NotEmpty(message = "Phone must not be empty")
    @Pattern(regexp = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})")
    private String phone;
    @NotEmpty(message = "Address must not be empty")
    private String address;
}
