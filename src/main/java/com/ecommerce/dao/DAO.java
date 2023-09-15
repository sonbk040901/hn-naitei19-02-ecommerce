package com.ecommerce.dao;

import com.ecommerce.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 09:12
 */
public interface DAO<PK extends Serializable, E extends BaseEntity> {
    List<E> get();
    E get(PK id);

    /**
     * Persist the given transient instance, create a new one if it doesn't exist, update if it already exists
     */
    void persist(E e) throws Exception;

    void delete(E e) throws Exception;
}
