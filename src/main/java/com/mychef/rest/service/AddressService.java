package com.mychef.rest.service;

import java.util.List;

import com.mychef.rest.entity.Address;

public interface AddressService {

    List<Address> getAddresses() throws Exception;

    String createAddress(Address address) throws Exception;

    Address updateAddress(Address address) throws Exception;

    Address getAddress(String addressID) throws Exception;

    String deleteAddress(String addressID) throws Exception;
}
