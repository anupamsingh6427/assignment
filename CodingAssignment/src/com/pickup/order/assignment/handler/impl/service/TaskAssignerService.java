/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.impl.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pickup.order.assignment.handler.api.constants.ApiConstants;
import com.pickup.order.assignment.handler.api.constants.DeliveryExecutiveStatusEnum;
import com.pickup.order.assignment.handler.api.constants.OrderStatusEnum;
import com.pickup.order.assignment.handler.api.constants.WorkAssignmentAlgoEnum;
import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.api.entities.ITaskAssignmentAlgorithm;
import com.pickup.order.assignment.handler.api.service.IExecutiveManagerService;
import com.pickup.order.assignment.handler.api.service.IOrderManagerService;
import com.pickup.order.assignment.handler.api.service.ITaskAssignerService;
import com.pickup.order.assignment.handler.api.service.ITaskAssignmentAlgoManagerService;
import com.pickup.order.assignment.handler.constants.DatabaseConstants;
import com.pickup.order.assignment.handler.util.DatabaseConnectorUtil;

/**
 *
 */
public class TaskAssignerService implements ITaskAssignerService {

    private WorkAssignmentAlgoEnum DEFAULT_ALGO_CONFIGURED = WorkAssignmentAlgoEnum.ORDER_DELAY_OPTIMIZER_QUALITY_ENHANCER_ALGO;
    private static TaskAssignerService instance = null;

    public static synchronized TaskAssignerService getInstance() {
        if (instance == null) {
            instance = new TaskAssignerService();
        }

        return instance;
    }

    private TaskAssignerService() {
    }

    @Override
    public Map<String, List<String>> assignPendingOrdersToExecutives(List<IOrderBean> pendingOrdersList,
            List<IExecutiveBean> availableExecutiveList) {
        ITaskAssignmentAlgorithm taskAssignmentAlgorithm = getTaskAssignmentAlgorithm();

        Map<String, List<String>> executiveIdVsAssignedOrdersMap = taskAssignmentAlgorithm
                .assignPendingOrdersToExecutives(pendingOrdersList, availableExecutiveList);

        updateOrderStatusForAssignedOrders(executiveIdVsAssignedOrdersMap.values());
        updateExecutiveStatusForTheseExecutives(executiveIdVsAssignedOrdersMap.keySet());

        return executiveIdVsAssignedOrdersMap;
    }

    private ITaskAssignmentAlgorithm getTaskAssignmentAlgorithm() {
        Map<String, Object> algoSelectionFilterMap = new HashMap<String, Object>();
        algoSelectionFilterMap.put(ApiConstants.ALGO_NAME, DatabaseConnectorUtil
                .fetchDatabaseConfigurationAsStringForKey(DatabaseConstants.WORK_ASSIGNMENT_ALGO,
                        DEFAULT_ALGO_CONFIGURED.getValue()));
        ITaskAssignmentAlgoManagerService taskAssignmentAlgoManagerService = TaskAssignmentAlgoManagerService
                .getInstance();

        ITaskAssignmentAlgorithm taskAssignmentAlgorithm = taskAssignmentAlgoManagerService
                .getTaskAssignmentAlgorithmForFilter(algoSelectionFilterMap);
        return taskAssignmentAlgorithm;
    }

    private void updateOrderStatusForAssignedOrders(Collection<List<String>> orderIds) {
        Set<String> uniqueOrderIdsSet = new HashSet<String>();
        for (List<String> orderIdsList : orderIds) {
            uniqueOrderIdsSet.addAll(orderIdsList);
        }
        IOrderManagerService orderManagerService = OrderManagerService.getInstance();
        orderManagerService.updateOrdersStatus(new ArrayList<String>(uniqueOrderIdsSet), OrderStatusEnum.ASSIGNED);
    }

    private void updateExecutiveStatusForTheseExecutives(Set<String> executiveIds) {
        IExecutiveManagerService executiveManagerService = ExecutiveManagerService.getInstance();
        executiveManagerService.updateDeliveryExecutivesStatus(new ArrayList<String>(executiveIds),
                DeliveryExecutiveStatusEnum.BUSY);
    }

}
