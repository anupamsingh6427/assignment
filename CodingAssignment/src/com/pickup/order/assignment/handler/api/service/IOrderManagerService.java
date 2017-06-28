/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 25, 2017
 */
package com.pickup.order.assignment.handler.api.service;

import java.util.List;

import com.pickup.order.assignment.handler.api.constants.OrderStatusEnum;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;

/**
 * Manages all the orders whose assignment is to be done
 * (keeps them in sorted order for simplicity)
 * Sorting order : Priority of order to be delivered
 * (like Order time + Customer priority like Gold Customer) 
 */
public interface IOrderManagerService {

    /**
     * Provides list of orders to be assigned to executives
     * sorted on the basis of order of delivery required
     * (Priority includes Order time and customer level)
     * @return list of orders
     */
    public List<IOrderBean> getPendingOrdersToBeAssigned();

    /**
     * Updates order status in Database
     * Removes assigned or delivered orders from cache
     * Loads next set of orders to be assigned in Application Cache
     * @param orderIdsListToBeUpdated
     * @param orderStatusToBeUpdated
     */
    public void updateOrdersStatus(List<String> orderIdsList, OrderStatusEnum orderStatus);

    /**
     * Adds the list of new orders in database which would be assigned to executives asap
     * Also updates the same in application cache if cache size permits the same
     */
    public void addNewOrdersForAssignment(List<IOrderBean> newOrdersList);
}
