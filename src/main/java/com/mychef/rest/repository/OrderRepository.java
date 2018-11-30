package com.mychef.rest.repository;

import java.util.List;

import com.mychef.rest.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:32 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findAllByStatus(String status);
}
