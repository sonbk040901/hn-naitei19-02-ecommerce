package com.ecommerce.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.BaseEntity;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 09:12
 */
public interface DAO<PK extends Serializable, E extends BaseEntity> extends JpaRepository<E, PK> {
}
