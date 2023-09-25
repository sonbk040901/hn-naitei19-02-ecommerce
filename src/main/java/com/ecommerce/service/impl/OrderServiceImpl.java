package com.ecommerce.service.impl;

import com.ecommerce.dto.FilterDTO;
import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ReceiverDTO;
import com.ecommerce.exception.NotFound;
import com.ecommerce.model.*;
import com.ecommerce.service.OrderService;
import org.modelmapper.TypeToken;
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
    public Page<OrderDTO> findOrdersByUserId(Long userId, FilterDTO filter) {
        Pageable pageable = getPageable(filter.getPage(), filter.getSize());
        Order.Status status = filter.getStatusValue() != -1 ? Order.Status.values()[filter.getStatusValue()] : null;
        Page<Order> orders = orderDAO.findAllByUserIdAndCreatedAtBetweenAndStatusLike(userId, filter.getFrom(), filter.getTo(), status, pageable);
        return orders.map(o -> {
            OrderDTO orderDTO = getMappedOrderDTO(o);
            List<OrderDetail> orderDetails = o.getOrderDetails();
            Product product = orderDetails.get(0).getProduct();
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            orderDTO.setFirstProduct(productDTO);
            return orderDTO;
        });
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
        long shippingFee = calculateShippingFee(orderDetails);
        long totalPrice = calculateTotalPrice(orderDetails, shippingFee);
        Receiver receiver = modelMapper.map(receiverDTO, Receiver.class);
        receiverDAO.save(receiver);
        Order order = new Order(totalPrice, null, shippingFee, receiver.getId(), userId, 0);
        //Lưu order và orderDetails
        orderDAO.save(order);
        orderDetails.forEach(orderDetail -> {
            orderDetail.setOrderId(order.getId());
            orderDetailDAO.save(orderDetail);
        });
        //Làm trống cart
        cartDAO.emptyCart(cart.getId());
        return getMappedOrderDTO(order);
    }

    private OrderDTO getMappedOrderDTO(Order order) {
        return modelMapper
                .typeMap(Order.class, OrderDTO.class)
                .addMapping(Order::getStatusValue, OrderDTO::setStatus)
                .map(order);
    }

    private static long calculateTotalPrice(List<OrderDetail> orderDetails, long shippingFee) {
        return orderDetails.stream().mapToLong(od -> od.getPrice() * od.getQuantity()).sum() + shippingFee;
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
