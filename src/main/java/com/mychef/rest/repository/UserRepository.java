package com.mychef.rest.repository;

import com.mychef.rest.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 9/28/2018 11:22 AM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}
