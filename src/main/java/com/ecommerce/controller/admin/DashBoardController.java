package com.ecommerce.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 02/10/2023
 * @Time: 19:00
 */
@Controller("adminDashBoardController")
@RequestMapping("/admin")
public class DashBoardController {
    @GetMapping
    public String index() {
        return "admin/index";
    }
}
