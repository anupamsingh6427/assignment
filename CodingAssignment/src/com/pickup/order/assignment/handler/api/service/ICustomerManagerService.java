/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.api.service;

import java.util.List;

import com.pickup.order.assignment.handler.api.entities.ICustomerBean;

/**
 * Manages all the customers
 */
public interface ICustomerManagerService {

    /**
     * Provides customer details for given customer id
     * @param customerId
     * @return customer details
     */
    public ICustomerBean getCustomerById(String customerId);

    /**
     * Adds provided customers to existing customers list
     * @param customersList
     */
    public void addCustomers(List<ICustomerBean> customersList);

    /**
     * Updates the given customer's details
     * @param customerBean
     * @return updated customer details
     */
    public ICustomerBean updateCustomer(ICustomerBean customerBean);
}
