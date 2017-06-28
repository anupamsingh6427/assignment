/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.algorithm;

import java.util.List;
import java.util.Map;

import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.api.entities.ITaskAssignmentAlgorithm;

/**
 *
 */
public class SelfDeducedOptimalAssignmentAlgorithm implements ITaskAssignmentAlgorithm {

    private final String algorithmName = "SELF_DEDUCE_BEST_ALGORITHM_FOR_WORK_ASSIGNMENT";

    @Override
    public String getAlgorithmName() {
        return algorithmName;
    }

    @Override
    public Map<String, List<String>> assignPendingOrdersToExecutives(List<IOrderBean> pendingOrdersList,
            List<IExecutiveBean> availableExecutivesList) {
        // TODO: anupam : IMPLEMENTATION
        // Algo : Try all the registered algos in the system and calculate their overall efficiency score for each algo's output
        // Now apply that algo which has highest efficiency score out of all the tried algos
        return null;
    }
}
