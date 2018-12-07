package com.mychef.rest.entity;

import com.mychef.rest.model.Customer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 9/28/2018 11:09 AM
 * Email: cuongnd@vega.com.vn
 * Project: mychef
 */
@Document(collection = "User1")
public class User extends BaseEntity {

    @Id
    public String _id;

    @Indexed(unique = true)
    private String email;
    private String password;
    private String name;
    private Baker baker;
    private Customer customer;

    public Baker getBaker() {
        return baker;
    }

    public void setBaker(Baker baker) {
        this.baker = baker;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
//                ", type='" + type + '\'' +
                '}';
    }
}
