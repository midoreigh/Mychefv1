package com.mychef.rest.entity;

import com.mychef.rest.model.FoodSpecs;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:11 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
@Document(collection = "Food")
public class Food {

    private Long _id;
    private String foodName;
    private String foodImage;
    private Float price;
    private String barkerId;
    private Baker baker;
    private FoodSpecs foodSpecs;
    private long available;
    private boolean approved;

    @Id
    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getBarkerId() {
        return barkerId;
    }

    public void setBarkerId(String barkerId) {
        this.barkerId = barkerId;
    }

    public Baker getBaker() {
        return baker;
    }

    public void setBaker(Baker baker) {
        this.baker = baker;
    }

    public FoodSpecs getFoodSpecs() {
        return foodSpecs;
    }

    public void setFoodSpecs(FoodSpecs foodSpecs) {
        this.foodSpecs = foodSpecs;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
