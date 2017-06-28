/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.api.entities;

import java.util.List;
import java.util.Map;

/**
 * You can implement your own algorithm by extending this interface
 * This approach will be really helpful while implementing Inversion_Of_Control design principle for providing
 * your own algo impl externally and simply registering that algo in the system and system will pick that algo
 */
public interface ITaskAssignmentAlgorithm {

    /**
     * Provides pending orders to executives mapping according to the implemented Algo
     * @param pendingOrdersList
     * @param availableExecutivesList
     * @return task assignment mapping (executiveId-orderId) according to the implemented Algo
     */
    public Map<String, List<String>> assignPendingOrdersToExecutives(List<IOrderBean> pendingOrdersList,
            List<IExecutiveBean> availableExecutivesList);

    public String getAlgorithmName();

}
