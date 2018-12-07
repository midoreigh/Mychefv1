package com.mychef.rest.controller;

import com.mychef.rest.common.ResponseDescription;
import com.mychef.rest.service.FoodService;
import com.mychef.rest.service.OrderService;
import com.mychef.rest.transform.CoreResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 12/7/2018 4:21 PM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
@Controller
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class FoodController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/discover", method = RequestMethod.GET)
    @ResponseBody
    public CoreResponse getFoods() {

        try {
            return new CoreResponse(this.foodService.findAll());
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }
}
