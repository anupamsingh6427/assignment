/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pickup.order.assignment.handler.algorithm.helper.AssignableScoreCalculator;
import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.api.entities.ITaskAssignmentAlgorithm;

/**
 * Assignable score : Efficiency of assigning an order to a executive (including First_mile_distance, DE_waiting_time, Order_delay_time)
 * Picks up the highest priority order and assigns the most efficient executive to it according to calculated assignable score
 * Then picks up the second highest priority order and assigns one of the best (most efficient) executive to it out of the remaining executives
 */
public class OrderDelayOptimizerAssignmentAlgorithm implements ITaskAssignmentAlgorithm {

    private final String algorithmName = "ORDER_DELAY_OPTIMIZER_QUALITY_ENHANCER_ALGO";

    @Override
    public String getAlgorithmName() {
        return algorithmName;
    }

    /**
     * Assignable score : Efficiency of assigning an order to a executive (including First_mile_distance, DE_waiting_time, Order_delay_time)
     * Picks up the highest priority order and assigns the most efficient executive to it according to calculated assignable score
     * Then picks up the second highest priority order and assigns one of the best (most efficient) executive to it out of the remaining executives
     */
    @Override
    public Map<String, List<String>> assignPendingOrdersToExecutives(List<IOrderBean> pendingOrdersList,
            List<IExecutiveBean> availableExecutivesList) {
        Map<String, List<String>> executiveIdVsAssignedOrdersMap = new HashMap<String, List<String>>();
        Map<String, Map<String, Double>> pendingOrderIdVsExecutiveIdVsAssignableScoreMap = AssignableScoreCalculator
                .calculateAssignableScoreForTheseOrdersAndAvailableExecutives(pendingOrdersList,
                        availableExecutivesList);
        for (Map.Entry<String, Map<String, Double>> pendingOrderIdVsExecutiveIdVsAssignableScoreEntry : pendingOrderIdVsExecutiveIdVsAssignableScoreMap
                .entrySet()) {
            String orderId = pendingOrderIdVsExecutiveIdVsAssignableScoreEntry.getKey();
            Map<String, Double> executiveIdsVsAssignableScoreMap = pendingOrderIdVsExecutiveIdVsAssignableScoreEntry
                    .getValue();
            Map<String, Double> availableExecutiveIdsVsAssignableScoreMap = removeAlreadyAssignedExecutivesFromAllExecutivesList(
                    executiveIdsVsAssignableScoreMap, executiveIdVsAssignedOrdersMap.keySet());
            String mostEfficientExecutive = getMostEfficientExecutiveIdAccordingToAssignableScore(availableExecutiveIdsVsAssignableScoreMap);
            List<String> assignedOrderIdsList = new ArrayList<String>();
            assignedOrderIdsList.add(orderId);
            executiveIdVsAssignedOrdersMap.put(mostEfficientExecutive, assignedOrderIdsList);
        }
        return executiveIdVsAssignedOrdersMap;
    }

    private Map<String, Double> removeAlreadyAssignedExecutivesFromAllExecutivesList(
            Map<String, Double> executiveIdsVsAssignableScoreMap, Set<String> alreadyAssignedExecutiveIds) {
        Map<String, Double> onlyAvailableExecutiveIdsVsAssignableScoreMap = new HashMap<String, Double>();
        if (alreadyAssignedExecutiveIds == null || alreadyAssignedExecutiveIds.isEmpty()) {
            onlyAvailableExecutiveIdsVsAssignableScoreMap.putAll(executiveIdsVsAssignableScoreMap);
        } else {
            for (Map.Entry<String, Double> executiveIdsVsAssignableScoreEntry : executiveIdsVsAssignableScoreMap
                    .entrySet()) {
                if (!alreadyAssignedExecutiveIds.contains(executiveIdsVsAssignableScoreEntry.getKey())) {
                    onlyAvailableExecutiveIdsVsAssignableScoreMap.put(executiveIdsVsAssignableScoreEntry.getKey(),
                            executiveIdsVsAssignableScoreEntry.getValue());
                }
            }
        }
        return onlyAvailableExecutiveIdsVsAssignableScoreMap;
    }

    private String getMostEfficientExecutiveIdAccordingToAssignableScore(
            Map<String, Double> availableExecutiveIdsVsAssignableScoreMap) {
        double MIN_ASSIGNABLE_SCORE = -1;
        String mostEfficientExecutiveId = null;
        for (Map.Entry<String, Double> availableExecutiveIdsVsAssignableScoreEntry : availableExecutiveIdsVsAssignableScoreMap
                .entrySet()) {
            String currentExecutiveId = availableExecutiveIdsVsAssignableScoreEntry.getKey();
            double currentExecutivesAssignableScore = availableExecutiveIdsVsAssignableScoreEntry.getValue();
            if (currentExecutivesAssignableScore > MIN_ASSIGNABLE_SCORE) {
                MIN_ASSIGNABLE_SCORE = currentExecutivesAssignableScore;
                mostEfficientExecutiveId = currentExecutiveId;
            }
        }
        return mostEfficientExecutiveId;
    }
}
