package com.ecommerce.controller;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 28/09/2023
 * @Time: 10:00
 */
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/auth/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/auth/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Validated UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/auth/signup";
        }
        userService.createUser(userDTO);
        return "redirect:/login?signup";
    }
}
