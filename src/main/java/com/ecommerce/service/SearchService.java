package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.ProductDTO;

public interface SearchService {
    List<ProductDTO> search(String name, Long minPrice, Long maxPrice, String category);
}
