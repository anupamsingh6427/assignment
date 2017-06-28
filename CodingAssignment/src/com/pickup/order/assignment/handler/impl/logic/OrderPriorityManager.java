/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.impl.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.bean.OrderBean;

/**
 *  Decides priority of the orders to be assigned
 */
public class OrderPriorityManager {

    /**
     *  Re-priortizes the pending orders list
     *  Based on Order delay time as well as customer level (premium customer etc)
     */
    public static List<OrderBean> appendOrdersListWithOrderPriortization(List<OrderBean> existingOrdersList,
            List<OrderBean> newOrdersToBeAppended) {
        List<OrderBean> finalAppendedPriortizedOrderList = new ArrayList<OrderBean>();
        finalAppendedPriortizedOrderList.addAll(existingOrdersList);
        finalAppendedPriortizedOrderList.addAll(newOrdersToBeAppended);
        Collections.sort(finalAppendedPriortizedOrderList, new Comparator<IOrderBean>() {

            @Override
            public int compare(IOrderBean orderBean1, IOrderBean orderBean2) {
                // you can use ICustomerManagerService.getCustomerById(orderBean.getAssociatedCustomerId()) to get customerLevel 
                // of both the orders and incorporate that as well while deciding the priority of these two orders here itself
                return orderBean1.getOrderTime().compareTo(orderBean2.getOrderTime());
            }
        });
        return finalAppendedPriortizedOrderList;
    }
}
