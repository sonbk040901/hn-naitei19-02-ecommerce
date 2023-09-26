package com.ecommerce.controller;

import com.ecommerce.dto.FilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    //Handle hiển thị giao diện thanh toán hóa đơn (aka tạo hóa đơn)
    @GetMapping("/new")
    public String showPaymentForm(@ModelAttribute("order") @Valid OrderDTO orderDTO, Model model) {
        orderDTO = orderService.initOrder(orderDTO);
        model.addAttribute("order", orderDTO);
        return "user/order/new";
    }

    @PostMapping
    public String createOrder(@ModelAttribute("order") @Valid OrderDTO orderDTO, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user/order/new";
        }
        OrderDTO order = orderService.createOrder(userId, orderDTO);
        redirectAttributes.addFlashAttribute("message", "Tạo hóa đơn thành công!");
        return "redirect:/orders/" + order.getId();
    }

    @GetMapping("/{id}")
    public String showSpecificOrder(@PathVariable Long id, Model model) {
        OrderDTO order = orderService.get(id);
        model.addAttribute("order", order);
        return "user/order/show";
    }

}
