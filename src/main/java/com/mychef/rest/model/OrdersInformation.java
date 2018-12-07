package com.mychef.rest.model;

import java.util.List;

import com.mychef.rest.entity.Order;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:38 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public class OrdersInformation {

    private List<Order> ongoingOrders;
    private List<Order> pastOrders;
    private Contact contact;

    public List<Order> getOngoingOrders() {
        return ongoingOrders;
    }

    public void setOngoingOrders(List<Order> ongoingOrders) {
        this.ongoingOrders = ongoingOrders;
    }

    public List<Order> getPastOrders() {
        return pastOrders;
    }

    public void setPastOrders(List<Order> pastOrders) {
        this.pastOrders = pastOrders;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
