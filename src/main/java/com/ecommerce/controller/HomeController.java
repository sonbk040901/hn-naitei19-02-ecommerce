package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.SearchService;

@Controller
public class HomeController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = { "/", "/{category}" })
    public ModelAndView index(@Param("name") String name, @Param("minPrice") Long minPrice,
            @Param("macPrice") Long maxPrice, @PathVariable(required = false) String category) {

        ModelAndView modelAndView = new ModelAndView("user/home");

        List<ProductDTO> products = searchService.search(name, minPrice, maxPrice, category);
        List<CategoryDTO> categories = categoryService.getAllByTree();

        modelAndView.addObject("categoryName", category);
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", categories);

        return modelAndView;
    }

}
