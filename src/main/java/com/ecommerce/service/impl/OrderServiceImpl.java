package com.ecommerce.service.impl;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 16/09/2023
 * @Time: 20:16
 */
@Service
public class OrderServiceImpl extends BaseService implements OrderService {
    @Override
    public List<OrderDTO> get() {
        return null;
    }

    @Override
    public OrderDTO get(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void save(OrderDTO e) {
        orderDAO.save(new Order(e));
    }

    @Override
    public void update(OrderDTO e) {

    }

    @Override
    public void delete(OrderDTO e) {

    }
}
