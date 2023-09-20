package com.ecommerce.service.impl;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.ReceiverDTO;
import com.ecommerce.exception.NotFound;
import com.ecommerce.model.*;
import com.ecommerce.service.OrderService;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Type listType = new TypeToken<List<OrderDTO>>() {
        }.getType();
        List<Order> orders = orderDAO.findAll();
        return modelMapper.map(orders, listType);
    }

    @Override
    public OrderDTO get(Long id) {
        Order order = orderDAO.findById(id).orElseThrow(() -> new NotFound("Order not found"));
        return modelMapper.map(order, OrderDTO.class);
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

    @Override
    public Page<OrderDTO> findOrdersByUserId(Long userId, int page, int size) {
        Pageable pageable = getPageable(page, size);
        Page<Order> orders = orderDAO.findAllByUserId(userId, pageable);
        return orders.map(order -> modelMapper.map(order, OrderDTO.class));
    }

    @Override
    @Transactional
    public OrderDTO createOrder(Long userId, ReceiverDTO receiverDTO) {
        Optional<Cart> cartOptional = cartDAO.findByUserId(userId);
        Cart cart;
        //Kiểm tra xem cart có tồn tại ko, nếu không thì tạo mới
        boolean isEmpty = cartOptional.isEmpty();
        if (isEmpty) {
            cart = new Cart(userId);
            cartDAO.save(cart);
            throw new NotFound("Cart is empty");
        }
        //Nếu có thì lấy cart đó ra
        cart = cartOptional.get();
        List<CartDetail> cartDetails = cart.getCartDetails();
        //Nếu cart đó không có sản phẩm nào thì báo lỗi
        if (cartDetails.isEmpty()) {
            throw new NotFound("Cart is empty");
        }
        List<OrderDetail> orderDetails = new ArrayList<>();
        mapCartDetailToOrderDetail(cartDetails, orderDetails);
        long totalPrice = orderDetails.stream().mapToLong(OrderDetail::getPrice).sum();
        long shippingFee = calculateShippingFee(orderDetails);
        Receiver receiver = modelMapper.map(receiverDTO, Receiver.class);
        Order order = new Order(totalPrice, null, shippingFee, receiver, userId, null);
        //Lưu order và orderDetails
        orderDAO.save(order);
        orderDetails.forEach(orderDetail -> {
            orderDetail.setOrderId(order.getId());
            orderDetailDAO.save(orderDetail);
        });
        //Làm trống cart
        cartDAO.emptyCart(cart.getId());
        return modelMapper.map(order, OrderDTO.class);
    }


    private void mapCartDetailToOrderDetail(CartDetail cartDetail, OrderDetail orderDetail) {
        Product product = cartDetail.getProduct();
        orderDetail.setPrice(product.getPrice());
        orderDetail.setQuantity(cartDetail.getQuantity());
        orderDetail.setProductId(product.getId());
    }

    private void mapCartDetailToOrderDetail(List<CartDetail> cartDetails, List<OrderDetail> orderDetails) {
        cartDetails.forEach(cartDetail1 -> {
            OrderDetail orderDetail = new OrderDetail();
            mapCartDetailToOrderDetail(cartDetail1, orderDetail);
            orderDetails.add(orderDetail);
        });
    }

    private long calculateShippingFee(List<OrderDetail> orderDetails) {
        return orderDetails.size() * 1000L;
    }
}
