package com.ecommerce.dao;

import com.ecommerce.model.BaseEntity;

import java.io.Serializable;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 09:12
 */
public interface DAO<E extends BaseEntity, Id extends Serializable> {
    E get(Id id);

    /**
     * Persist the given transient instance, create a new one if it doesn't exist, update if it already exists
     */
    void persist(E e);

    void delete(E e);
}
