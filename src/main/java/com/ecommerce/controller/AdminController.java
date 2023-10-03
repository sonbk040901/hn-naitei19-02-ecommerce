package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("")
	public String showAllByAdmin(Model model) {
		List<OrderDTO> orderDTOS = orderService.showAllByAdmin();
		model.addAttribute("orders", orderDTOS);
		return "admin/order";
	}
	
	@GetMapping("/{orderId}")
	public String showOrderDetail(@PathVariable Long orderId, Model model) {
		OrderDTO orderDTO = orderService.getOrderDetail(orderId);
		OrderDTO checkOrder = null;
		if (!orderDTO.equals(checkOrder)) 	
		{
			model.addAttribute("order", orderDTO);
			model.addAttribute("receiver", orderDTO.getReceiver());
			model.addAttribute("total", orderDTO.getTotalPrice() + orderDTO.getShippingFee());
			model.addAttribute("orderDetails", orderDTO.getOrderDetails());
			return "admin/order-detail";
		}
		
			model.addAttribute("header", "Lỗi hiển thị đơn hàng");
			model.addAttribute("message", "Đơn hàng không tồn tại hoặc đã bị xóa");
			return "error/index";
	}
}
