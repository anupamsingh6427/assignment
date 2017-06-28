/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.impl.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pickup.order.assignment.handler.api.constants.OrderStatusEnum;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.api.service.IOrderManagerService;
import com.pickup.order.assignment.handler.bean.OrderBean;
import com.pickup.order.assignment.handler.impl.logic.OrderPriorityManager;

/**
 * Database and cache syncing is not implemented as of now in this service
 * Please check ExecutiveManagerService if you want to see cache implementation in sync with Database
 */
public class OrderManagerService implements IOrderManagerService {

    private List<OrderBean> pendingOrdersList = null; //Only PENDING orders are stored in this list
    private static OrderManagerService instance = null;

    public static synchronized OrderManagerService getInstance() {
        if (instance == null) {
            instance = new OrderManagerService();
        }

        return instance;
    }

    private OrderManagerService() {
        pendingOrdersList = new ArrayList<OrderBean>();
    }

    @Override
    public List<IOrderBean> getPendingOrdersToBeAssigned() {
        List<IOrderBean> pendingOrdersClonedList = new ArrayList<IOrderBean>();
        synchronized (pendingOrdersList) {
            for (OrderBean orderBean : pendingOrdersList) {
                if (OrderStatusEnum.PENDING == orderBean.getOrderStatus()) {
                    OrderBean clonedOrderBean = new OrderBean(orderBean);
                    pendingOrdersClonedList.add(clonedOrderBean);
                }
            }
        }
        return pendingOrdersClonedList;
    }

    @Override
    public void updateOrdersStatus(List<String> orderIdsList, OrderStatusEnum orderStatus) {
        switch (orderStatus) {
            case PENDING:
                // please invoke addNewOrdersForAssignment() API
                break;
            case ASSIGNED:
            case DELIVERED:
                removeTheseOrdersFromPendingOrdersList(orderIdsList);
                break;
            default:
                break;
        }
    }

    @Override
    public void addNewOrdersForAssignment(List<IOrderBean> newOrdersList) {
        List<OrderBean> ordersToBeAddedClonedList = new ArrayList<OrderBean>();
        for (IOrderBean newOrderBean : newOrdersList) {
            OrderBean newOrderClonedBean = new OrderBean(newOrderBean);
            ordersToBeAddedClonedList.add(newOrderClonedBean);
        }
        synchronized (pendingOrdersList) {
            pendingOrdersList = OrderPriorityManager.appendOrdersListWithOrderPriortization(pendingOrdersList,
                    ordersToBeAddedClonedList);
        }
    }

    private void removeTheseOrdersFromPendingOrdersList(List<String> orderIdsList) {
        if (orderIdsList == null || orderIdsList.isEmpty()) {
            // TODO: invalid operation. log and throw exception
            return;
        }
        synchronized (pendingOrdersList) {
            Iterator<OrderBean> iterator = pendingOrdersList.iterator();
            while (iterator.hasNext()) {
                OrderBean orderBean = iterator.next();
                if (orderBean.getOrderId() != null && orderIdsList.contains(orderBean.getOrderId())) {
                    iterator.remove();
                }
            }
        }
    }

}
