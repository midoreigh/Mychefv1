package com.mychef.rest.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.mychef.rest.common.ResponseDescription;
import com.mychef.rest.common.UserType;
import com.mychef.rest.model.Item;
import com.mychef.rest.service.OrderService;
import com.mychef.rest.transform.CoreResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public CoreResponse getOrders(HttpServletRequest request) {

        try {
            boolean isBaker = false;
            if (request.getHeader("userType") != null && request.getHeader("userType").equalsIgnoreCase(UserType.BAKER_USER_TYPE)) {
                isBaker = true;
            }
            return new CoreResponse(this.orderService.getOrdersInformation(isBaker));
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    @ResponseBody
    public CoreResponse placeOrder(@RequestBody Map<String, List<Item>> foods) {

        try {
            this.orderService.placeOrder(foods);
            return new CoreResponse(true, null);
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }
}
