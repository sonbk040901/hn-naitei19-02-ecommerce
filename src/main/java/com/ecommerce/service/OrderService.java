package com.ecommerce.service;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.ReceiverDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 16/09/2023
 * @Time: 20:15
 */
public interface OrderService extends Service<Long, OrderDTO> {
    OrderDTO createOrder(Long userId, ReceiverDTO receiver);
    Page<OrderDTO> findOrdersByUserId(Long userId, int page, int size);
}
