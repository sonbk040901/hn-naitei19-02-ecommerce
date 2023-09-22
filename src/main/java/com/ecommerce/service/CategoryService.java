package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.CategoryDTO;

public interface CategoryService {
    List<CategoryDTO> getAllByTree();
}
