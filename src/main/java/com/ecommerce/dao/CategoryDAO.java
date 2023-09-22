package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Category;

@Repository
public interface CategoryDAO extends DAO<Long, Category> {

    @Query("SELECT c FROM Category c WHERE c.parent IS NULL")
    List<Category> getAllByTree();
}
