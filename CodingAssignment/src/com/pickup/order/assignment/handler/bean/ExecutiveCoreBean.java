/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.bean;

import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;

/**
 *
 */
public abstract class ExecutiveCoreBean implements IExecutiveBean {

    private String executiveId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public ExecutiveCoreBean() {
    }

    public ExecutiveCoreBean(String executiveId, String firstName, String lastName, String phone, String email) {
        this.executiveId = executiveId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(String executiveId) {
        this.executiveId = executiveId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
