package com.mychef.rest.service;

import java.util.Map;

import com.mychef.rest.entity.Baker;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Thursday, 12/6/2018 9:29 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public interface BakerService {

    Map<String, Object> saveBaker(Baker baker);
}
