package com.ecommerce.service;

import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 09:05
 */
public interface Service<PK, E> {
    List<E> get();

    E get(PK id);

    void save(E e);

    void update(E e);

    void delete(E e);
}
