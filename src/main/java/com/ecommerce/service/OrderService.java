package com.ecommerce.service;

import com.ecommerce.dto.FilterDTO;
import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.ReceiverDTO;
import org.springframework.data.domain.Page;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 16/09/2023
 * @Time: 20:15
 */
public interface OrderService extends Service<Long, OrderDTO> {
    OrderDTO createOrder(Long userId, ReceiverDTO receiver);
    Page<OrderDTO> findOrdersByUserId(Long userId, FilterDTO filterDTO);
}
