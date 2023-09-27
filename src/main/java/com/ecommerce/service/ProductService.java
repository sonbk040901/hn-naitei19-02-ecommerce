package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService extends Service<Long, ProductDTO> {
    Page<ProductDTO> findAllProducts(int page, int size);

    boolean checkProductAvailable(Long id, int quantity);
}
