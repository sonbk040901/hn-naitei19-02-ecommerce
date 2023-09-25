package com.ecommerce.controller;

import com.ecommerce.dto.FilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.ReceiverDTO;
import com.ecommerce.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    private final Long userId = 9L; // fixme: get userId from session/cookie

    @GetMapping
    public String showAllOrders(
            @Valid FilterDTO filter,
            Model model) {
        Page<OrderDTO> orders = orderService.findOrdersByUserId(userId, filter);
        System.err.println(filter);
        model.addAttribute("orders", orders.getContent());
        model.addAttribute("currentPage", orders.getNumber() + 1);
        model.addAttribute("totalItems", orders.getTotalElements());
        model.addAttribute("totalPages", orders.getTotalPages());
        model.addAttribute("pageSize", filter.getSize());
        var from = orders.getNumber() * orders.getPageable().getPageSize() + 1;
        model.addAttribute("from", from);
        model.addAttribute("to", from + orders.getNumberOfElements() - 1);
        model.addAttribute("filter", filter);
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
