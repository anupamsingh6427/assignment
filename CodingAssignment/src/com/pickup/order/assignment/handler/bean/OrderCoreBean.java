/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.bean;

import java.util.Date;

import com.pickup.order.assignment.handler.api.entities.IOrderBean;

/**
 *
 */
public abstract class OrderCoreBean implements IOrderBean {

    private String orderId;
    private Date orderTime;
    private String associatedCustomerId;
    private String associatedRestaurantId;

    public OrderCoreBean() {
    }

    public OrderCoreBean(String orderId, Date orderTime, String associatedCustomerId, String associatedRestaurantId) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.associatedCustomerId = associatedCustomerId;
        this.associatedRestaurantId = associatedRestaurantId;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getAssociatedCustomerId() {
        return associatedCustomerId;
    }

    public void setAssociatedCustomerId(String associatedCustomerId) {
        this.associatedCustomerId = associatedCustomerId;
    }

    @Override
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String getAssociatedRestaurantId() {
        return associatedRestaurantId;
    }

    public void setAssociatedRestaurantId(String associatedRestaurantId) {
        this.associatedRestaurantId = associatedRestaurantId;
    }

}
