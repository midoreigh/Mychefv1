package com.mychef.rest.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mychef.rest.common.OrderStatus;
import com.mychef.rest.entity.Baker;
import com.mychef.rest.entity.Food;
import com.mychef.rest.entity.Order;
import com.mychef.rest.entity.User;
import com.mychef.rest.model.Item;
import com.mychef.rest.model.OrdersInformation;
import com.mychef.rest.repository.FoodRepository;
import com.mychef.rest.repository.OrderRepository;
import com.mychef.rest.service.OrderService;
import com.mychef.rest.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public OrdersInformation getOrdersInformation(boolean isBaker) {

        User user = this.userService.getProfile();
        OrdersInformation ordersInformation = new OrdersInformation();
        List<Order> ongoingOrders = this.findAllByStatus(OrderStatus.PREPARING_ORDER_STATUS, user.get_id(), isBaker);
        List<Order> pastOrders = this.findAllByStatus(OrderStatus.DELIVERED_ORDER_STATUS, user.get_id(), isBaker);
        ordersInformation.setOngoingOrders(ongoingOrders);
        ordersInformation.setPastOrders(pastOrders);
        return ordersInformation;
    }

    @Override
    public List<Order> findAllByStatus(String status, String userId, boolean isBaker) {
        List<Order> orders;
        if (isBaker) {
            orders = orderRepository.findAllByStatusAndBakerId(status, userId);
        } else {
            orders = orderRepository.findAllByStatusAndCustomerId(status, userId);
        }

        if (orders != null && orders.size() > 0) {
            orders.stream().map(order -> {

                if (!ObjectUtils.isEmpty(order.getItems())) {
                    int total = 0;
                    for (int i = 0; i < order.getItems().size(); i++) {
                        total += order.getItems().get(i).getCount();
                    }
                    order.setTotal(total);
                }
                return order;
            }).collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public void placeOrder(Map<String, List<Item>> foods) throws Exception {
        if (foods.get("foodItems") == null || foods.get("foodItems").size() < 1) {
            throw new Exception("List of foods is empty");
        }
        List<Item> foodItems = foods.get("foodItems");
        Optional<Food> foodOptional = this.foodRepository.findById(foodItems.get(0).getFoodId());
        if (!foodOptional.isPresent()) {
            throw new Exception("Food is not exist with id " + foodItems.get(0).getFoodId());
        }

        Food food = foodOptional.get();
        Baker baker = foodOptional.get().getBaker();

        User user = this.userService.getProfile();

        Order order = new Order();
        order.setStatus(OrderStatus.PREPARING_ORDER_STATUS);
        order.setBakerId(food.getBarkerId());
        order.setBaker(baker);
        order.setCustomerId(user.get_id());
        order.setCustomer(user.getCustomer());
        Food dbFood;
        for (Item item : foodItems) {
            foodOptional = this.foodRepository.findById(item.getFoodId());
            if (!foodOptional.isPresent()) {
                throw new Exception("Food is not exist with id " + item.getFoodId());
            }
            dbFood = foodOptional.get();
            item.setFoodName(dbFood.getFoodName());
        }

        order.setItems(foodItems);

        this.orderRepository.save(order);
    }

    @Override
    public boolean orderStatus(Order order) throws Exception {

        if (order.get_id() == null) {
            throw new Exception("OrderID is empty");
        }

        Optional<Order> dbOrderOptional = this.orderRepository.findById(order.get_id());
        if (!dbOrderOptional.isPresent()) {
            throw new Exception("Order is not exist");
        }

        Order dbOrder = dbOrderOptional.get();
        dbOrder.setStatus(order.getStatus());
        this.orderRepository.save(dbOrder);
        return true;
    }
}
