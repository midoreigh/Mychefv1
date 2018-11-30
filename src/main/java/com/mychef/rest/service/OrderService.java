package com.mychef.rest.service;

import java.util.List;

import com.mychef.rest.entity.Order;
import com.mychef.rest.model.OrdersInformation;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:40 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public interface OrderService {

    OrdersInformation getOrdersInformation();

    List<Order> findAllByStatus(String status);

}
