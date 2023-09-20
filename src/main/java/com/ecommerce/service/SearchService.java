package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.ProductDTO;

public interface SearchService {
    List<ProductDTO> search(String name, Integer minPrice, Integer maxPrice);
}
