/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 27, 2017
 */
package com.pickup.order.assignment.handler.impl.service;

import java.util.List;
import java.util.Map;

import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.api.service.IExecutiveManagerService;
import com.pickup.order.assignment.handler.api.service.IOrderAssignmentTimerService;
import com.pickup.order.assignment.handler.api.service.IOrderManagerService;
import com.pickup.order.assignment.handler.api.service.ITaskAssignerService;

/**
 *
 */
public class OrderAssignmentTimerService implements IOrderAssignmentTimerService {
    private static OrderAssignmentTimerService instance = null;

    public static synchronized OrderAssignmentTimerService getInstance() {
        if (instance == null) {
            instance = new OrderAssignmentTimerService();
        }

        return instance;
    }

    private OrderAssignmentTimerService() {
    }

    /**
     * This function is exepcted to continuosly keep on assigning pending orders to executives
     * Either as a timer service
     * Or by listening the events of newOrderPlaced and newExecutiveBecameAvailable
     */
    @Override
    public void startOrderAssignmentToExecutives() {
        // Assumption : As soon as a pair of pending order and available executive is found in the system,
        // it will make an assignment for the same without waiting for next orders or executives to become available
        // It will be changed in live system
        do {
            IOrderManagerService orderManagerSevice = OrderManagerService.getInstance();
            List<IOrderBean> pendingOrdersList = orderManagerSevice.getPendingOrdersToBeAssigned();
            IExecutiveManagerService executiveManagerService = ExecutiveManagerService.getInstance();
            List<IExecutiveBean> availableExecutivesList = executiveManagerService.getAllAvailableExecutives();

            ITaskAssignerService taskAssignerService = TaskAssignerService.getInstance();
            Map<String, List<String>> executiveIdVsAssignedOrdersMap = taskAssignerService
                    .assignPendingOrdersToExecutives(pendingOrdersList, availableExecutivesList);
            printOrderToExecutiveAssignmentAsOutput(executiveIdVsAssignedOrdersMap);
        } while (isAnyOrderPendingForAssignment());
        //This loop should not go on and on rather it should be invoked after a certain fixed duration of time just like a Timer Service
    }

    private static boolean isAnyOrderPendingForAssignment() {
        // it will continuosly check it from IOrderManagerService.getPendingOrdersToBeAssigned()
        // Stopping this loop for now
        return false;
    }

    private static void printOrderToExecutiveAssignmentAsOutput(Map<String, List<String>> executiveIdVsAssignedOrdersMap) {
        System.out.println("Assignment round result (ExecutiveId - OrderId) : \n");
        for (Map.Entry<String, List<String>> executiveIdVsAssignedOrdersEntry : executiveIdVsAssignedOrdersMap
                .entrySet()) {
            String executiveId = executiveIdVsAssignedOrdersEntry.getKey();
            List<String> assignedOrders = executiveIdVsAssignedOrdersEntry.getValue();
            for (String assignedOrderId : assignedOrders) {
                System.out.println(executiveId + "   -   " + assignedOrderId);
            }
        }
    }

}
