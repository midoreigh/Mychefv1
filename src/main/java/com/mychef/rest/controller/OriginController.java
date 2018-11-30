package com.mychef.rest.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.mychef.rest.common.ResponseDescription;
import com.mychef.rest.entity.User;
import com.mychef.rest.exception.CoreException;
import com.mychef.rest.service.UserService;
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

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 9/28/2018 11:40 AM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class OriginController {
    private Logger logger = LogManager.getLogger(getClass());


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CoreResponse register(@RequestBody User user) {

        try {
            logger.info(" User: " + user.toString());
            Map<String, Object> map = new HashMap<>();
            map.put("user", this.userService.register(user));
            return new CoreResponse(map);
        } catch (CoreException e) {
            e.printStackTrace();
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CoreResponse login(@RequestBody User user,
                              HttpServletResponse response) {


        try {
            logger.info(" User: " + user.toString());
            Map<String, Object> map = new HashMap<>();
            map.put("user", this.userService.login(user));
            return new CoreResponse(map);
        } catch (Exception e) {
            logger.info(" exc controller");
            logger.info(e.getMessage(), e);
            return new CoreResponse(ResponseDescription.ERROR_STATUS, e.getMessage());
        }

    }
}
