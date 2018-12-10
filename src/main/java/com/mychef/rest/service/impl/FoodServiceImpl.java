package com.mychef.rest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mychef.rest.entity.Food;
import com.mychef.rest.entity.User;
import com.mychef.rest.repository.FoodRepository;
import com.mychef.rest.service.FoodService;
import com.mychef.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:35 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserService userService;

    @Override
    public Map<String, List<Food>> findAll() {

        Map<String, List<Food>> map = new HashMap<>();
        map.put("foods", this.foodRepository.findAll());
        return map;
    }

    @Override
    public Food saveFood(Food food) {

        User user = this.userService.getProfile();
        food.setBaker(user.getBaker());
        food.setBarkerId(user.get_id());
        food.setApproved(true);
        return this.foodRepository.save(food);
    }
}
