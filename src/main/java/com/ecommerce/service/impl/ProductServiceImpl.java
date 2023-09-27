package com.ecommerce.service.impl;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.exception.NotFound;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseService implements ProductService {
    public Page<ProductDTO> findAllProducts(int page, int size) {
        Pageable pageable = getPageable(page, size);
        Page<Product> products = productDAO.findAll(pageable);
        return products.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @Override
    public boolean checkProductAvailable(Long id, int quantity) {
        Optional<Product> productOptional = productDAO.findById(id);
        return productOptional.isPresent() && productOptional.get().getQuantity() >= quantity;
    }

    @Override
    public List<ProductDTO> get() {
        return null;
    }

    @Override
    public ProductDTO get(Long id) {
        Product product = productDAO.findById(id).orElseThrow(() -> new NotFound("Product not found"));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public void save(ProductDTO e) {

    }

    @Override
    public void update(ProductDTO e) {

    }

    @Override
    public void delete(ProductDTO e) {

    }
}
