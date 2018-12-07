package com.mychef.rest.model;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:29 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public class Item {

    private Long foodId;
    private String foodName;
    private int count;
    private float price;

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
