package com.ecommerce.controller;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.ReceiverDTO;
import com.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 22:50
 */
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {
    private final OrderService orderService;
    private final Long userId = 1L; //fixme: get userId from session/cookie

    @GetMapping
    public String showAllOrders(
            @RequestParam(name = "page", required = false) Optional<Integer> page,
            @RequestParam(name = "size", required = false) Optional<Integer> size,
            Model model) {
        Page<OrderDTO> orders = orderService.findOrdersByUserId(userId, page.orElse(1), size.orElse(10));
        model.addAttribute("orders", orders);
        return "user/order/index";
    }

    @PostMapping
    public String createNewOrderFromCart(@ModelAttribute @Valid ReceiverDTO receiver) {
        OrderDTO order = orderService.createOrder(userId, receiver);
        return "redirect:/orders/" + order.getId();
    }

    @GetMapping("/{id}")
    public String showSpecificOrder(@PathVariable Long id, Model model) {
        OrderDTO order = orderService.get(id);
        model.addAttribute("order", order);
        return "user/order/show";
    }


}
