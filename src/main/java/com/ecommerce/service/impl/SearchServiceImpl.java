package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.model.Product;
import com.ecommerce.service.SearchService;

@Service
public class SearchServiceImpl extends BaseService implements SearchService {

    @Override
    public List<ProductDTO> search(String name, Integer minPrice, Integer maxPrice) {
        List<Product> list = productDAO.findByConditions(name, minPrice, maxPrice);
        return list.stream().map(this::mapToProductDTO).toList();
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }
}
