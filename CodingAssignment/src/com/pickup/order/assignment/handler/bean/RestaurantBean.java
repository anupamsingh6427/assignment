/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.bean;

import com.pickup.order.assignment.handler.api.entities.IRestaurantBean;

/**
 *
 */
public class RestaurantBean implements IRestaurantBean {

    private String restaurantId;
    private String restaurantName;
    private double restaurantLocationsLatitude;
    private double restaurantLocationsLongitude;

    public RestaurantBean() {
    }

    public RestaurantBean(String restaurantId, String restaurantName, double restaurantLocationsLatitude,
            double restaurantLocationsLongitude) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantLocationsLatitude = restaurantLocationsLatitude;
        this.restaurantLocationsLongitude = restaurantLocationsLongitude;
    }

    public RestaurantBean(IRestaurantBean restaurantBean) {
        this.restaurantId = restaurantBean.getRestaurantId();
        this.restaurantName = restaurantBean.getRestaurantName();
        this.restaurantLocationsLatitude = restaurantBean.getRestaurantLocationsLatitude();
        this.restaurantLocationsLongitude = restaurantBean.getRestaurantLocationsLongitude();
    }

    @Override
    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public double getRestaurantLocationsLatitude() {
        return restaurantLocationsLatitude;
    }

    public void setRestaurantLocationsLatitude(double restaurantLocationsLatitude) {
        this.restaurantLocationsLatitude = restaurantLocationsLatitude;
    }

    @Override
    public double getRestaurantLocationsLongitude() {
        return restaurantLocationsLongitude;
    }

    public void setRestaurantLocationsLongitude(double restaurantLocationsLongitude) {
        this.restaurantLocationsLongitude = restaurantLocationsLongitude;
    }

    @Override
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
