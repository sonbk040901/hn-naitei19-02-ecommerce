package com.ecommerce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class PageController {

	@RequestMapping("/")
	public String homepage() {
		return "user/index";
	}
}
