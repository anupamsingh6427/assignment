/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 27, 2017
 */
package com.pickup.order.assignment.handler.api.service;

/**
 * Continuosly handles order assignments to executives
 */
public interface IOrderAssignmentTimerService {

    /**
     * This function is exepcted to continuosly keep on assigning pending orders to executives
     * Either as a timer service
     * Or by listening the events of newOrderPlaced and newExecutiveBecameAvailable
     */
    public void startOrderAssignmentToExecutives();
}
