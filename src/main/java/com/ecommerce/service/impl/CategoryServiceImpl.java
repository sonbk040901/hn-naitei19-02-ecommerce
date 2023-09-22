package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {

    public List<CategoryDTO> getAllByTree() {
        List<Category> list = categoryDAO.getAllByTree();
        return list.stream().map(this::mapToCategoryDTO).toList();
    }

    private CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }
}
