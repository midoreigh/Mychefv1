package com.mychef.rest.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.mychef.rest.entity.Baker;
import com.mychef.rest.entity.User;
import com.mychef.rest.repository.UserRepository;
import com.mychef.rest.service.BakerService;
import com.mychef.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Thursday, 12/6/2018 9:30 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
@Service
public class BakerServiceImpl implements BakerService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, Object> saveBaker(Baker baker) {

        User user = this.userService.getProfile();
        baker.setBakerName(user.getName());
        user.setBaker(baker);
        this.userRepository.save(user);
        Map<String, Object> result = new HashMap<>();
        result.put("approval", "Pending");
        return result;
    }
}
