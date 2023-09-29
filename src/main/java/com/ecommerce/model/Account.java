package com.ecommerce.model;

import com.ecommerce.dto.BaseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

//@EqualsAndHashCode(callSuper = true)
//@Data
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "accounts")
//@Builder
public class Account extends BaseEntity {
    @Getter
    @RequiredArgsConstructor
    public enum Gender {
        MALE(0),
        FEMALE(1),
        OTHER(2);
        private final int value;
    }
    @Getter
    @RequiredArgsConstructor
    public enum Role {
        USER(0),
        ADMIN(1);
        private final Integer value;
    }

    private String username;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    @ColumnDefault("0")
    private Role role = Role.USER;

    @Column(name = "is_actived")
    @ColumnDefault("false")
    private Boolean isActived;

    private String fullname;

    private String avatar;

    @Column(columnDefinition = "char(10)")
    private String phone;

    private String address;
    @ColumnDefault("2")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender = Gender.OTHER;

    public Account(BaseDTO dto) {
        super(dto);
    }

    public Account(String username, String password, Integer role, Boolean isActived, String fullname, String avatar, String phone, String address, Integer gender) {
        this.username = username;
        this.password = password;
        this.role = Role.values()[role];
        this.isActived = isActived;
        this.fullname = fullname;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
        this.gender = Gender.values()[gender];
    }

    public Account(String username, String password, Role role, Boolean isActived, String fullname, String avatar, String phone, String address, Gender gender) {
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
    public int getGenderValue(){
        return gender.value;
    }
    public int getRoleValue(){
        return role.value;
    }
}
