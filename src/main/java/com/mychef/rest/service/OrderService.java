package com.mychef.rest.service;

import java.util.List;
import java.util.Map;

import com.mychef.rest.entity.Order;
import com.mychef.rest.model.Item;
import com.mychef.rest.model.OrdersInformation;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:40 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public interface OrderService {

    OrdersInformation getOrdersInformation(boolean isBaker);

    List<Order> findAllByStatus(String status, String userId, boolean isBaker);

    void placeOrder(Map<String, List<Item>> foods) throws Exception;

    boolean orderStatus(Order order) throws Exception;
}
