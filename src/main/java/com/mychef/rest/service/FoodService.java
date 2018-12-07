package com.mychef.rest.service;

import java.util.List;
import java.util.Map;

import com.mychef.rest.entity.Food;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:34 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public interface FoodService {

    Map<String, List<Food>> findAll();

    Food saveFood(Food food);
}
