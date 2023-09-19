package com.ecommerce.dao.impl;

import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.hibernate.Session;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 15:34
 */
@Getter
public abstract class BaseDAO {
    @PersistenceContext
    protected Session session;
}
