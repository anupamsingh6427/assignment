/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 25, 2017
 */
package com.pickup.order.assignment.handler.api.entities;

import java.util.Date;

import com.pickup.order.assignment.handler.api.constants.OrderStatusEnum;

/**
 * Represents a single order details
 */
public interface IOrderBean {

    public String getOrderId();

    public Date getOrderTime();

    public String getAssociatedCustomerId();

    public String getAssociatedRestaurantId();

    public OrderStatusEnum getOrderStatus();

}
