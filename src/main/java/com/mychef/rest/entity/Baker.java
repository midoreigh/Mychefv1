package com.mychef.rest.entity;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:16 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public class Baker {

    private String bakerName;
    private String bakerImage;
    private Address address;

    public String getBakerName() {
        return bakerName;
    }

    public void setBakerName(String bakerName) {
        this.bakerName = bakerName;
    }

    public String getBakerImage() {
        return bakerImage;
    }

    public void setBakerImage(String bakerImage) {
        this.bakerImage = bakerImage;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
