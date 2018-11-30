package com.mychef.rest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mychef.rest.entity.Food;
import com.mychef.rest.repository.FoodRepository;
import com.mychef.rest.service.FoodService;
import org.springframework.stereotype.Service;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:35 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
@Service
public class FoodServiceImpl implements FoodService {

    private FoodRepository foodRepository;

    @Override
    public Map<String, List<Food>> findAll() {

        Map<String, List<Food>> map = new HashMap<>();
        map.put("foods", this.foodRepository.findAll());
        return map;
    }
}
