package com.mychef.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.mychef.rest.entity.Address;
import com.mychef.rest.entity.User;
import com.mychef.rest.repository.UserRepository;
import com.mychef.rest.service.AddressService;
import com.mychef.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Address> getAddresses() throws Exception {
        User user = this.userService.getProfile();
        if (user == null) {
            throw new Exception("User is not exist.");
        }

        List<Address> addresses = user.getAddresses();
        if (addresses == null || addresses.size() == 0) {
            throw new Exception("You haven't have any address.");
        }

        return addresses;
    }

    @Override
    public String createAddress(Address address) throws Exception {

        User user = this.userService.getProfile();
        if (user == null) {
            throw new Exception("User is not exist.");
        }

        List<Address> addresses = user.getAddresses();
        if (addresses == null) {
            addresses = new ArrayList<>();
            user.setAddresses(addresses);
        }

        address.setAddressID(String.valueOf(System.nanoTime()));
        address.setCreatedBy(user.get_id());
        address.setCreatedOn(new Date());
        addresses.add(address);
        this.userRepository.save(user);
        return address.getAddressID();
    }

    @Override
    public Address updateAddress(Address address) throws Exception {

        if (StringUtils.isEmpty(address.getAddressID())) {
            throw new Exception("Address is not empty.");
        }

        User user = this.userService.getProfile();
        if (user == null) {
            throw new Exception("User is not exist.");
        }

        List<Address> addresses = user.getAddresses();
        if (addresses == null || addresses.size() == 0) {
            throw new Exception("You haven't have any address.");
        }

        List<Address> filterAddresses = addresses.stream().filter(a -> a.getAddressID().equals(address.getAddressID())).collect(Collectors.toList());
        if (filterAddresses == null || filterAddresses.size() == 0) {
            throw new Exception("This address is not exist.");
        }

        Address dbAddress = filterAddresses.get(0);
        dbAddress.mapData(address);
        dbAddress.setModifiedBy(user.get_id());
        dbAddress.setModifiedOn(new Date());
        this.userRepository.save(user);
        return dbAddress;
    }

    @Override
    public Address getAddress(String addressID) throws Exception {
        User user = this.userService.getProfile();
        if (user == null) {
            throw new Exception("User is not exist.");
        }

        List<Address> addresses = user.getAddresses();
        if (addresses == null || addresses.size() == 0) {
            throw new Exception("You haven't have any address.");
        }

        List<Address> filterAddresses = addresses.stream().filter(a -> a.getAddressID().equals(addressID)).collect(Collectors.toList());
        if (filterAddresses == null || filterAddresses.size() == 0) {
            throw new Exception("This address is not exist.");
        }

        Address dbAddress = filterAddresses.get(0);

        return dbAddress;
    }

    @Override
    public String deleteAddress(String addressID) throws Exception {

        User user = this.userService.getProfile();
        if (user == null) {
            throw new Exception("User is not exist.");
        }

        List<Address> addresses = user.getAddresses();
        if (addresses == null || addresses.size() == 0) {
            throw new Exception("You haven't have any address.");
        }

        List<Address> filterAddresses = addresses.stream().filter(a -> a.getAddressID().equals(addressID)).collect(Collectors.toList());
        if (filterAddresses == null || filterAddresses.size() == 0) {
            throw new Exception("This address is not exist.");
        }

        Address dbAddress = filterAddresses.get(0);
        addresses.remove(dbAddress);

        this.userRepository.save(user);
        return dbAddress.getAddressID();
    }
}
