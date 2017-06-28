/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.api.entities;

import com.pickup.order.assignment.handler.api.constants.CustomerLevelEnum;

/**
 * Represents a single customer as an entity
 */
public interface ICustomerBean {

    public String getCustomerId();

    public String getFirstName();

    public String getLastName();

    public String getEmail();

    public String getPhone();

    public CustomerLevelEnum getCustomerLevel();
}
