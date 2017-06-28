/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.bean;

import java.util.Date;

import com.pickup.order.assignment.handler.api.constants.DeliveryExecutiveStatusEnum;
import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;

/**
 *
 */
public class ExecutiveBean extends ExecutiveCoreBean {

    private DeliveryExecutiveStatusEnum executiveStatus;
    private Date lastOrderDeliveredTime;
    private double executiveLocationsLatitude;
    private double executiveLocationsLongitude;

    public ExecutiveBean() {
        super();
    }

    public ExecutiveBean(String executiveId, String firstName, String lastName, String phone, String email,
            DeliveryExecutiveStatusEnum executiveStatus, Date lastOrderDeliveredTime,
            double executiveLocationsLatitude, double executiveLocationsLongitude) {
        super(executiveId, firstName, lastName, phone, email);
        this.executiveStatus = executiveStatus;
        this.lastOrderDeliveredTime = lastOrderDeliveredTime;
        this.executiveLocationsLatitude = executiveLocationsLatitude;
        this.executiveLocationsLongitude = executiveLocationsLongitude;
    }

    public ExecutiveBean(IExecutiveBean executiveBean) {
        super(executiveBean.getExecutiveId(), executiveBean.getFirstName(), executiveBean.getLastName(), executiveBean
                .getPhone(), executiveBean.getEmail());
        this.executiveStatus = executiveBean.getExecutiveStatus();
        this.lastOrderDeliveredTime = executiveBean.getLastOrderDeliveredTime();
        this.executiveLocationsLatitude = executiveBean.getExecutiveLocationsLatitude();
        this.executiveLocationsLongitude = executiveBean.getExecutiveLocationsLongitude();
    }

    @Override
    public DeliveryExecutiveStatusEnum getExecutiveStatus() {
        return executiveStatus;
    }

    public void setExecutiveStatus(DeliveryExecutiveStatusEnum executiveStatus) {
        this.executiveStatus = executiveStatus;
    }

    @Override
    public double getExecutiveLocationsLatitude() {
        return executiveLocationsLatitude;
    }

    public void setExecutiveLocationsLatitude(double executiveLocationsLatitude) {
        this.executiveLocationsLatitude = executiveLocationsLatitude;
    }

    @Override
    public double getExecutiveLocationsLongitude() {
        return executiveLocationsLongitude;
    }

    public void setExecutiveLocationsLongitude(double executiveLocationsLongitude) {
        this.executiveLocationsLongitude = executiveLocationsLongitude;
    }

    @Override
    public Date getLastOrderDeliveredTime() {
        return lastOrderDeliveredTime;
    }

    public void setLastOrderDeliveredTime(Date lastOrderDeliveredTime) {
        this.lastOrderDeliveredTime = lastOrderDeliveredTime;
    }
}
