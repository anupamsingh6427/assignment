/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.impl.service;

import java.util.HashMap;
import java.util.Map;

import com.pickup.order.assignment.handler.api.constants.ApiConstants;
import com.pickup.order.assignment.handler.api.entities.ITaskAssignmentAlgorithm;
import com.pickup.order.assignment.handler.api.service.ITaskAssignmentAlgoManagerService;
import com.pickup.order.assignment.handler.util.StringUtils;

/**
 *
 */
public class TaskAssignmentAlgoManagerService implements ITaskAssignmentAlgoManagerService {

    Map<String, ITaskAssignmentAlgorithm> algoNameVsAlgoMap = null; //this data structure will be changed to incorporate other algo filters like area name etc
    private static TaskAssignmentAlgoManagerService instance = null;

    public static synchronized TaskAssignmentAlgoManagerService getInstance() {
        if (instance == null) {
            instance = new TaskAssignmentAlgoManagerService();
        }
        return instance;
    }

    private TaskAssignmentAlgoManagerService() {
        algoNameVsAlgoMap = new HashMap<String, ITaskAssignmentAlgorithm>();
    }

    @Override
    public String registerNewAlgorithmForTaskAssignment(ITaskAssignmentAlgorithm taskAssignmentAlgorithm) {
        if (StringUtils.isStringNullOrEmpty(taskAssignmentAlgorithm.getAlgorithmName())) {
            return null;
            //log and throw exception
        }
        synchronized (algoNameVsAlgoMap) {
            //overriding existing algo if it exists with the exact same algo name
            algoNameVsAlgoMap.put(taskAssignmentAlgorithm.getAlgorithmName(), taskAssignmentAlgorithm);
        }
        return taskAssignmentAlgorithm.getAlgorithmName();
    }

    @Override
    public ITaskAssignmentAlgorithm getTaskAssignmentAlgorithmForFilter(Map<String, Object> algoSelectionFilter) {
        ITaskAssignmentAlgorithm taskAssignmentAlgorithm = null;
        if (algoSelectionFilter.containsKey(ApiConstants.ALGO_NAME)) {
            String algoName = String.valueOf(algoSelectionFilter.get(ApiConstants.ALGO_NAME));
            synchronized (algoNameVsAlgoMap) {
                taskAssignmentAlgorithm = algoNameVsAlgoMap.get(algoName);
            }
        }
        return taskAssignmentAlgorithm;
    }
}
