/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.api.entities;

/**
 * Represents a single restaurant as an entity
 */
public interface IRestaurantBean {

    public String getRestaurantId();

    public String getRestaurantName();

    public double getRestaurantLocationsLatitude();

    public double getRestaurantLocationsLongitude();
}
