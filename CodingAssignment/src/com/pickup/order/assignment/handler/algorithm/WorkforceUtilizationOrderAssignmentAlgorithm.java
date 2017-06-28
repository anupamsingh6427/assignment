/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pickup.order.assignment.handler.algorithm.helper.AssignableScoreCalculator;
import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.api.entities.ITaskAssignmentAlgorithm;
import com.pickup.order.assignment.handler.util.DataStructureModifierUtil;

/**
 * Assignable score : Efficiency of assigning an order to a executive (including First_mile_distance, DE_waiting_time, Order_delay_time)
 * Picks up the executive (with highest waiting_time) and assigns the best possible/efficient order to him according to calculated assignable score
 * Then picks up the second highest waiting time executive and assigns one of the best (most efficient) order to him out of the remaining pending orders
 */
public class WorkforceUtilizationOrderAssignmentAlgorithm implements ITaskAssignmentAlgorithm {

    private final String algorithmName = "WORKFORCE_UTILIZATION_ORDER_ASSIGNMENT_ALGO";

    @Override
    public String getAlgorithmName() {
        return algorithmName;
    }

    /**
     * Assignable score : Efficiency of assigning an order to a executive (including First_mile_distance, DE_waiting_time, Order_delay_time)
     * Picks up the executive (with highest waiting_time) and assigns the best possible/efficient order to him according to calculated assignable score
     * Then picks up the second highest waiting time executive and assigns one of the best (most efficient) order to him out of the remaining pending orders
     */
    @Override
    public Map<String, List<String>> assignPendingOrdersToExecutives(List<IOrderBean> pendingOrdersList,
            List<IExecutiveBean> availableExecutivesList) {
        Map<String, List<String>> executiveIdVsAssignedOrdersMap = new HashMap<String, List<String>>();
        Map<String, Map<String, Double>> pendingOrderIdVsExecutiveIdVsAssignableScoreMap = AssignableScoreCalculator
                .calculateAssignableScoreForTheseOrdersAndAvailableExecutives(pendingOrdersList,
                        availableExecutivesList);
        Map<String, Map<String, Double>> executiveIdVsPendingOrderIdVsAssignableScoreMap = DataStructureModifierUtil
                .swapKeysInTheMap(pendingOrderIdVsExecutiveIdVsAssignableScoreMap);
        for (Map.Entry<String, Map<String, Double>> executiveIdVsPendingOrderIdVsAssignableScoreEntry : executiveIdVsPendingOrderIdVsAssignableScoreMap
                .entrySet()) {
            String currentExecutiveId = executiveIdVsPendingOrderIdVsAssignableScoreEntry.getKey();
            Map<String, Double> orderIdsVsAssignableScoreMap = executiveIdVsPendingOrderIdVsAssignableScoreEntry
                    .getValue();
            Map<String, Double> pendingOrderIdsVsAssignableScoreMap = removeAlreadyAssignedOrdersFromAllOrdersList(
                    orderIdsVsAssignableScoreMap, executiveIdVsAssignedOrdersMap.values());
            String mostEfficientOrderId = getMostEfficientOrderIdToBeAssignedAccordingToAssignableScore(pendingOrderIdsVsAssignableScoreMap);
            if (executiveIdVsAssignedOrdersMap.containsKey(currentExecutiveId)) {
                List<String> assignedOrderIdsToCurrentExecutive = executiveIdVsAssignedOrdersMap
                        .get(currentExecutiveId);
                assignedOrderIdsToCurrentExecutive.add(mostEfficientOrderId);
            } else {
                List<String> assignOrderIdsToCurrentExecutive = new ArrayList<String>();
                assignOrderIdsToCurrentExecutive.add(mostEfficientOrderId);
                executiveIdVsAssignedOrdersMap.put(currentExecutiveId, assignOrderIdsToCurrentExecutive);
            }
        }
        return executiveIdVsAssignedOrdersMap;
    }

    private Map<String, Double> removeAlreadyAssignedOrdersFromAllOrdersList(
            Map<String, Double> orderIdsVsAssignableScoreMap, Collection<List<String>> assignedOrderIdsCollection) {
        Map<String, Double> onlyPendingIdsVsAssignableScoreMap = new HashMap<String, Double>();
        Set<String> assignedOrderIdsSet = new HashSet<String>();
        if (assignedOrderIdsCollection != null && !assignedOrderIdsCollection.isEmpty()) {
            for (List<String> orderIdsList : assignedOrderIdsCollection) {
                assignedOrderIdsSet.addAll(orderIdsList);
            }
        }
        if (assignedOrderIdsSet.isEmpty()) {
            onlyPendingIdsVsAssignableScoreMap.putAll(orderIdsVsAssignableScoreMap);
        } else {
            for (Map.Entry<String, Double> orderIdsVsAssignableScoreEntry : orderIdsVsAssignableScoreMap.entrySet()) {
                if (!assignedOrderIdsSet.contains(orderIdsVsAssignableScoreEntry.getKey())) {
                    onlyPendingIdsVsAssignableScoreMap.put(orderIdsVsAssignableScoreEntry.getKey(),
                            orderIdsVsAssignableScoreEntry.getValue());
                }
            }
        }
        return onlyPendingIdsVsAssignableScoreMap;
    }

    private String getMostEfficientOrderIdToBeAssignedAccordingToAssignableScore(
            Map<String, Double> pendingOrderIdsVsAssignableScoreMap) {
        double MIN_ASSIGNABLE_SCORE = -1;
        String mostEfficientOrderId = null;
        for (Map.Entry<String, Double> pendingOrderIdsVsAssignableScoreEntry : pendingOrderIdsVsAssignableScoreMap
                .entrySet()) {
            String currentOrderId = pendingOrderIdsVsAssignableScoreEntry.getKey();
            double currentOrdersAssignableScore = pendingOrderIdsVsAssignableScoreEntry.getValue();
            if (currentOrdersAssignableScore > MIN_ASSIGNABLE_SCORE) {
                MIN_ASSIGNABLE_SCORE = currentOrdersAssignableScore;
                mostEfficientOrderId = currentOrderId;
            }
        }
        return mostEfficientOrderId;
    }
}
