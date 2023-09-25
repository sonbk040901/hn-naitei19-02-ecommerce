package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
public class PageController {
    @RequestMapping("/")
    public String homepage() {
        return "user/index";
    }
}
