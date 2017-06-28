/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.impl.service;

import java.util.List;

import com.pickup.order.assignment.handler.api.entities.ICustomerBean;
import com.pickup.order.assignment.handler.api.service.ICustomerManagerService;

/**
 *
 */
public class CustomerManagerService implements ICustomerManagerService {

    /** 
     * Singleton
     */
    private static CustomerManagerService instance = null;

    /** 
     * Singleton
     */
    public static synchronized CustomerManagerService getInstance() {
        if (instance == null) {
            instance = new CustomerManagerService();
        }

        return instance;
    }

    /** 
     * Singleton
     */
    private CustomerManagerService() {
    }

    @Override
    public ICustomerBean getCustomerById(String customerId) {
        // TODO: anupam. impl
        return null;
    }

    @Override
    public void addCustomers(List<ICustomerBean> customersList) {
        // TODO: anupam. impl
    }

    @Override
    public ICustomerBean updateCustomer(ICustomerBean customerBean) {
        // TODO: anupam. impl
        return null;
    }
}
