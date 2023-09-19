package com.ecommerce.model;

import com.ecommerce.dto.BaseDTO;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    private String username;

    private String password;

    private Integer role;

    @Column(name = "is_actived")
    private Integer isActived;

    private String fullname;

    private String avatar;

    private String phone;

    private String address;

    private Integer gender;

    public Account(BaseDTO dto) {
        super(dto);
    }

    public Account(String username, String password, Integer role, Integer isActived, String fullname, String avatar, String phone, String address, Integer gender) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActived = isActived;
        this.fullname = fullname;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }
}
