package com.mychef.rest.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.mychef.rest.entity.User;
import com.mychef.rest.exception.CoreException;
import com.mychef.rest.model.CustomUserDetails;
import com.mychef.rest.model.UserTokenState;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {
    User findById(String id);

    User findByUsername(String username);

    List<User> findAll();

    UserTokenState register(User user) throws CoreException;

    UserTokenState login(User user) throws CoreException;

    User getProfile();

    CustomUserDetails getCustomUserDetails();

    UserTokenState refreshToken(HttpServletRequest request) throws CoreException, Exception;
}
