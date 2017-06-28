/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.api.service;

import java.util.Map;

import com.pickup.order.assignment.handler.api.entities.ITaskAssignmentAlgorithm;

/**
 * Manages all the registered algos in the system for order assignment to executives
 * This service enables the programmer to provide their own algorithm (using Inversion_of_Control design principle)
 */
public interface ITaskAssignmentAlgoManagerService {

    /**
     * Registers new Algorithm
     * @param taskAssignmentAlgorithm
     * @return algorithmUniqueRegistrationId
     */
    public String registerNewAlgorithmForTaskAssignment(ITaskAssignmentAlgorithm taskAssignmentAlgorithm);

    /**
     * Provides algorithm for given filter
     * Filter includes Algo_Name or Area_Name (Area_Name filter not implemented as of now)
     * @param algoSelectionFilter
     * @return Algorithm for task assignment
     */
    public ITaskAssignmentAlgorithm getTaskAssignmentAlgorithmForFilter(Map<String, Object> algoSelectionFilter);
}
