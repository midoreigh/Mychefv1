package com.mychef.rest.controller;

import com.mychef.rest.common.ResponseDescription;
import com.mychef.rest.entity.Baker;
import com.mychef.rest.entity.Food;
import com.mychef.rest.entity.Order;
import com.mychef.rest.service.BakerService;
import com.mychef.rest.service.FoodService;
import com.mychef.rest.service.OrderService;
import com.mychef.rest.transform.CoreResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Thursday, 12/6/2018 9:33 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
@Controller
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class BarkerController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private BakerService bakerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/baker", method = RequestMethod.POST)
    @ResponseBody
    public CoreResponse saveBaker(@RequestBody Baker baker) {

        try {
            return new CoreResponse(this.bakerService.saveBaker(baker));
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

    @RequestMapping(value = "/orderStatus", method = RequestMethod.POST)
    @ResponseBody
    public CoreResponse orderStatus(@RequestBody Order order) {

        try {
            return new CoreResponse(this.orderService.orderStatus(order), null);
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }


    @RequestMapping(value = "/food", method = RequestMethod.POST)
    @ResponseBody
    public CoreResponse saveFood(@RequestBody Food food) {

        try {
            return new CoreResponse(this.foodService.saveFood(food));
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }


}
