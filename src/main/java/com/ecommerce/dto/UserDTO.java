package com.ecommerce.dto;

import com.ecommerce.model.BaseEntity;
import lombok.*;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 16/09/2023
 * @Time: 13:06
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO {
    private Long id;
    private String username;
    private String password;
    private Integer role;
    private Integer isActived;
    private String fullname;
    private String avatar;
    private String phone;
    private String address;
    private Integer gender;

    public UserDTO(BaseEntity entity) {
        super(entity);
    }
}
