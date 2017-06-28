/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.api.constants;

/**
 *
 */
public enum DeliveryExecutiveStatusEnum {
    AVAILABLE, // Available for task assignment and still not picked by server from database
    BUSY, // Order delivery in progress
    LEAVE, // Executive is not available
    PICKED_FOR_ASSIGNMENT; // updated in Database when server loads this executive in cache for assignment
}
