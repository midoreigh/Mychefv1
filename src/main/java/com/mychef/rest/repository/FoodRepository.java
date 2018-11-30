package com.mychef.rest.repository;

import com.mychef.rest.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:20 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public interface FoodRepository extends MongoRepository<Food, Long> {
}
