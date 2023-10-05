package com.ecommerce.service;

import com.ecommerce.dto.FilterDTO;
import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.ReceiverDTO;
import com.ecommerce.model.Account;
import com.ecommerce.userdetails.CustomUserDetails;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 16/09/2023
 * @Time: 20:15
 */
public interface OrderService extends Service<Long, OrderDTO> {
    @Transactional
    OrderDTO createOrder(Long userId, OrderDTO orderDTO);

    OrderDTO initOrder(OrderDTO orderDTO);
    OrderDTO initOrder(Account user, List<? extends Long> cartDetailIds);

    Page<OrderDTO> findOrdersByUserId(Long userId, FilterDTO filterDTO);

    @Transactional
    void cancelOrder(Long id, Long userId);

    int getOrderSize(CustomUserDetails user);
    
	List<OrderDTO> showAllByAdmin();
	
	OrderDTO getOrderDetail(Long orderId);
}
