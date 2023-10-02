package com.ecommerce.dao;

import com.ecommerce.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 15:44
 */

@Repository
public interface AccountDAO extends DAO<Long, Account> {
    Optional<Account> findFirstByUsername(String username);
    Page<Account> findAllByAddress(Pageable pageable, String address);
}
