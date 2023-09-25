package com.ecommerce.service.impl;

import com.ecommerce.dao.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 15:33
 */
@Getter
@Setter
public abstract class BaseService {
    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    protected AccountDAO accountDAO;
    @Autowired
    protected OrderDAO orderDAO;
    @Autowired
    protected CartDAO cartDAO;
    @Autowired
    protected CartDetailDAO cartDetailDAO;
    @Autowired
    protected OrderDetailDAO orderDetailDAO;
    @Autowired
    protected ReceiverDAO receiverDAO;

    protected Pageable getPageable(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    protected Pageable getPageable(int page, int size, Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }

    @Autowired
    protected ProductDAO productDAO;

    @Autowired
    protected CategoryDAO categoryDAO;
}
