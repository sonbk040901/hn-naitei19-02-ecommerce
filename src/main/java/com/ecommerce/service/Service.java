package com.ecommerce.service;

import com.ecommerce.dto.BaseDTO;

import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 09:05
 */
public interface Service<PK, D extends BaseDTO> {
    List<D> get();

    D get(PK id);

    void save(D e);

    void update(D e);

    void delete(D e);
}
