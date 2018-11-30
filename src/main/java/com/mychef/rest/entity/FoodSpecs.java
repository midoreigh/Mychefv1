package com.mychef.rest.entity;

/**
 * Author: Nguyen Duc Cuong
 * Create date: Friday, 11/30/2018 11:17 AM
 * Email: cuongnd@vega.com.vn
 * Project: rest
 */
public class FoodSpecs {

    private String foodCategory;
    private String spiceLevel;
    private String allergy;

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getSpiceLevel() {
        return spiceLevel;
    }

    public void setSpiceLevel(String spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }
}
