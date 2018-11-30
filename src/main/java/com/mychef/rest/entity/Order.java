package com.mychef.rest.entity;

import java.util.List;

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
    private Baker baker;
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
}
