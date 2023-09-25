package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                        ((:name IS NULL OR p.name like %:name% ) OR
                        (:name IS NULL OR p.category.name like %:name% ) OR
                        (:name IS NULL OR p.category.parent.name like %:name% )) AND
                        (:minPrice IS NULL OR p.price >= :minPrice ) AND
                        (:maxPrice IS NULL OR p.price <= :maxPrice ) AND
                        (:numberOfSale IS NULL OR p.numberOfSale >= :numberOfSale ) AND
                        ((:category IS NULL OR p.category.name = :category ) OR
                        (:category IS NULL OR p.category.parent.name = :category ))
                        """)
        Page<Product> findByConditions(String name, Long minPrice, Long maxPrice, String category,
                        Integer numberOfSale, Pageable pageable);

        @Query("select p from Product p order by p.numberOfSale desc limit 4")
        List<Product> findTopSale();
}
