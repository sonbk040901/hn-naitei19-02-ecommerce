package com.ecommerce.userdetails;

import com.ecommerce.model.Account;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 28/09/2023
 * @Time: 17:28
 */
@Getter
@Setter
public class CustomUserDetails extends User {
    private Account user;

    public CustomUserDetails(@NonNull Account user) {
        this(user.getUsername(), user.getPassword(),
                mapperRolesToAuthorities(user.getRole().name()));
        this.user = user;
    }

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    private static Collection<? extends GrantedAuthority> mapperRolesToAuthorities(String value) {
        return List.of(new SimpleGrantedAuthority(value));
    }
}
