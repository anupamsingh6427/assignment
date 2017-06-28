/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 25, 2017
 */
package com.pickup.order.assignment.handler.api.service;

import java.util.List;
import java.util.Map;

import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;

/**
 * Responsible for assigning orders to all Delivery Executives optimally
 */
public interface ITaskAssignerService {

    /**
     * Assigns pending orders to available executives based on provided Algorithm
     * @param pendingOrdersList
     * @param availableExecutiveList
     * @return executiveIdVsListOfOrdersAssignedToHim
     */
    public Map<String, List<String>> assignPendingOrdersToExecutives(List<IOrderBean> pendingOrdersList,
            List<IExecutiveBean> availableExecutiveList);
}
