/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 25, 2017
 */
package com.pickup.order.assignment.handler.api.entities;

import java.util.Date;

import com.pickup.order.assignment.handler.api.constants.DeliveryExecutiveStatusEnum;

/**
 * Represents an individual Delivery Executive
 */
public interface IExecutiveBean {

    public String getExecutiveId();

    public String getFirstName();

    public String getLastName();

    public String getPhone();

    public String getEmail();

    public DeliveryExecutiveStatusEnum getExecutiveStatus();

    public double getExecutiveLocationsLatitude();

    public double getExecutiveLocationsLongitude();

    public Date getLastOrderDeliveredTime();
}
