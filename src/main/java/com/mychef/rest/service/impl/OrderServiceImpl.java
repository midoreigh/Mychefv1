package com.mychef.rest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.mychef.rest.common.OrderStatus;
import com.mychef.rest.entity.Order;
import com.mychef.rest.model.OrdersInformation;
import com.mychef.rest.repository.OrderRepository;
import com.mychef.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:41 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrdersInformation getOrdersInformation() {

        OrdersInformation ordersInformation = new OrdersInformation();
        List<Order> ongoingOrders = this.findAllByStatus(OrderStatus.PREPARING_ORDER_STATUS);
        List<Order> pastOrders = this.findAllByStatus(OrderStatus.DELIVERED_ORDER_STATUS);
        ordersInformation.setOngoingOrders(ongoingOrders);
        ordersInformation.setPastOrders(pastOrders);
        return ordersInformation;
    }

    @Override
    public List<Order> findAllByStatus(String status) {
        List<Order> orders = orderRepository.findAllByStatus(status).stream().map(order -> {

            if (!ObjectUtils.isEmpty(order.getItems())) {
                int total = 0;
                for (int i = 0; i < order.getItems().size(); i++) {
                    total += order.getItems().get(i).getCount();
                }
                order.setTotal(total);
            }
            return order;
        }).collect(Collectors.toList());

        return orders;
    }
}
