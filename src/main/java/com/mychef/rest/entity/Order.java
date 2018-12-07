package com.mychef.rest.entity;

import java.util.List;

import com.mychef.rest.model.Customer;
import com.mychef.rest.model.Item;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Monday, 10/15/2018 10:41 AM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
@Document(collection = "Order")
public class Order extends BaseEntity {

    private Long _id;
    private String bakerId;
    private Baker baker;
    private String customerId;
    private Customer customer;
    private String status;
    private List<Item> items;
    private int total;

    @Id
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getBakerId() {
        return bakerId;
    }

    public void setBakerId(String bakerId) {
        this.bakerId = bakerId;
    }

    public Baker getBaker() {
        return baker;
    }

    public void setBaker(Baker baker) {
        this.baker = baker;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
