package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Product;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 18/09/2023
 * @Time: 17:46
 */
@Repository
public interface ProductDAO extends DAO<Long, Product> {

        @Query("""
                        select p from Product p where
                        (:name IS NULL OR p.name like %:name% ) AND
                        (:minPrice IS NULL OR p.price >:minPrice ) AND
                        (:maxPrice IS NULL OR p.price <:maxPrice )
                        """)
        List<Product> findByConditions(String name, Integer minPrice, Integer maxPrice);
}
