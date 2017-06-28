/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.bean;

import java.util.Date;

import com.pickup.order.assignment.handler.api.constants.OrderStatusEnum;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;

/**
 *
 */
public class OrderBean extends OrderCoreBean {

    private OrderStatusEnum orderStatus;

    public OrderBean() {
        super();
    }

    public OrderBean(String orderId, Date orderTime, String associatedCustomerId, String associatedRestaurantId,
            OrderStatusEnum orderStatus) {
        super(orderId, orderTime, associatedCustomerId, associatedRestaurantId);
        this.orderStatus = orderStatus;
    }

    public OrderBean(IOrderBean orderBean) {
        super(orderBean.getOrderId(), orderBean.getOrderTime(), orderBean.getAssociatedCustomerId(), orderBean
                .getAssociatedRestaurantId());
        this.orderStatus = orderBean.getOrderStatus();
    }

    @Override
    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

}
