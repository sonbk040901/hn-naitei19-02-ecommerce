package com.ecommerce.dto;


import com.ecommerce.validator.PasswordMatch;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@PasswordMatch
@AllArgsConstructor
public class UserDTO extends BaseDTO {
    @Size(min = 6, max = 64, message = "Tên đăng nhập phải từ 6 đến 64 ký tự")
    private String username;
    @Size(min = 6, max = 64, message = "Mật khẩu phải từ 6 đến 64 ký tự")
    private String password;
    private String confirmPassword;
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
