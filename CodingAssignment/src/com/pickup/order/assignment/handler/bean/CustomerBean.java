/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.bean;

import com.pickup.order.assignment.handler.api.constants.CustomerLevelEnum;
import com.pickup.order.assignment.handler.api.entities.ICustomerBean;

/**
 *
 */
public class CustomerBean implements ICustomerBean {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private CustomerLevelEnum customerLevel;

    public CustomerBean() {
    }

    public CustomerBean(String customerId, String firstName, String lastName, String email, String phone,
            CustomerLevelEnum customerLevel) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.customerLevel = customerLevel;
    }

    @Override
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public CustomerLevelEnum getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(CustomerLevelEnum customerLevel) {
        this.customerLevel = customerLevel;
    }
}
