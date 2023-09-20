package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.service.SearchService;

import ch.qos.logback.core.model.Model;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public ModelAndView search(@Param("name") String name, @Param("minPrice") Integer minPrice,
            @Param("macPrice") Integer maxPrice, Model model) {

        List<ProductDTO> products = searchService.search(name, minPrice, maxPrice);

        ModelAndView modelAndView = new ModelAndView("search");

        modelAndView.addObject("name", name);
        modelAndView.addObject("minPrice", minPrice);
        modelAndView.addObject("maxPrice", maxPrice);
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
