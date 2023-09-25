package com.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.model.Product;

public interface SearchService {
    Page<Product> search(String name, Long minPrice, Long maxPrice, String category, Integer numberOfSale,
            Pageable pageable);

    List<ProductDTO> findTopSale();

    List<ProductDTO> mapProductDTOs(List<Product> products);
}
