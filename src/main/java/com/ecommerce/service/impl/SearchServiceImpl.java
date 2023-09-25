package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.model.Product;
import com.ecommerce.service.SearchService;

@Service
public class SearchServiceImpl extends BaseService implements SearchService {

    @Override
    public Page<Product> search(String name, Long minPrice, Long maxPrice, String category, Integer numberOfSale,
            Pageable pageable) {
        Page<Product> pages = productDAO.findByConditions(name, minPrice, maxPrice, category, numberOfSale,
                pageable);
        return pages;
    }

    @Override
    public List<ProductDTO> findTopSale() {
        List<Product> products = productDAO.findTopSale();
        return mapProductDTOs(products);
    }

    @Override
    public List<ProductDTO> mapProductDTOs(List<Product> products) {
        return products.stream().map(this::mapToProductDTO).toList();
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }
}
