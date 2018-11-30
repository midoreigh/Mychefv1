package com.mychef.rest.controller;

import com.mychef.rest.common.ResponseDescription;
import com.mychef.rest.entity.Address;
import com.mychef.rest.service.AddressService;
import com.mychef.rest.service.FoodService;
import com.mychef.rest.service.OrderService;
import com.mychef.rest.transform.CoreResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodService foodService;


    @RequestMapping(value = "/address", method = RequestMethod.GET)
    @ResponseBody
    public CoreResponse getAddresses() {

        try {
            return new CoreResponse(this.addressService.getAddresses());
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

    @RequestMapping(value = "/address/{addressID}", method = RequestMethod.GET)
    @ResponseBody
    public CoreResponse getAddress(@PathVariable(value = "addressID") String addressID) {

        try {
            return new CoreResponse(this.addressService.getAddress(addressID));
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

    @RequestMapping(value = "/address/create", method = RequestMethod.POST)
    @ResponseBody
    public CoreResponse createAddress(@RequestBody Address address) {

        try {
            return new CoreResponse(this.addressService.createAddress(address));
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

    @RequestMapping(value = "/address/update", method = RequestMethod.POST)
    @ResponseBody
    public CoreResponse updateAddress(@RequestBody Address address) {

        try {
            return new CoreResponse(this.addressService.updateAddress(address));
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

    @RequestMapping(value = "/address/{addressID}/delete", method = RequestMethod.GET)
    @ResponseBody
    public CoreResponse deleteAddress(@PathVariable(value = "addressID") String addressID) {

        try {
            return new CoreResponse(this.addressService.deleteAddress(addressID));
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public CoreResponse getOrders() {

        try {
            return new CoreResponse(this.orderService.getOrdersInformation());
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

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
