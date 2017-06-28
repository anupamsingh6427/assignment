/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.api.service;

import java.util.List;

import com.pickup.order.assignment.handler.api.entities.IRestaurantBean;

/**
 * Manages list of partener restaurants
 */
public interface IRestaurantManagerService {

    /**
     * Provides restaurant details for the given restaurantId
     * @param restaurantId
     * @return restaurant details
     */
    public IRestaurantBean getRestaurantById(String restaurantId);

    /**
     * Appends provided restaurants to existing restaurants list
     * @param restaurantsList
     * @return restaurantids
     */
    public List<String> addRestaurantList(List<IRestaurantBean> restaurantListToBeAdded);
}
