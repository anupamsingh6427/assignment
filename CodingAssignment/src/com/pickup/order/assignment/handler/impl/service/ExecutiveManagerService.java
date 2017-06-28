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

import com.pickup.order.assignment.handler.api.constants.DeliveryExecutiveStatusEnum;
import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.service.IExecutiveManagerService;
import com.pickup.order.assignment.handler.bean.ExecutiveBean;
import com.pickup.order.assignment.handler.impl.logic.ExecutivePriorityManager;

/**
 *
 */
public class ExecutiveManagerService implements IExecutiveManagerService {

    private List<ExecutiveBean> availableExecutivesList = null;
    private static ExecutiveManagerService instance = null;

    //private static final int MAX_CACHE_SIZE_DEFAULT_CONFIG = 1000; //maximum 1000 executives could be loaded in cache at a time

    public static synchronized ExecutiveManagerService getInstance() {
        if (instance == null) {
            instance = new ExecutiveManagerService();
        }
        return instance;
    }

    private ExecutiveManagerService() {
        availableExecutivesList = new ArrayList<ExecutiveBean>();
        recoverDatabase(); // handling random server restarts
        loadCache(); //Load cache from database while service is coming up
    }

    @Override
    public List<IExecutiveBean> getAllAvailableExecutives() {
        return cloneAvailableExecutivesList();
    }

    @Override
    public void updateDeliveryExecutivesStatus(List<String> deliveryExecutiveIdsList,
            DeliveryExecutiveStatusEnum executiveStatus) {
        if (executiveStatus == null || DeliveryExecutiveStatusEnum.PICKED_FOR_ASSIGNMENT == executiveStatus) {
            // TODO: invalid operation. Log and Throw exception
            // External services do not have priviledge to update the status to PICKED_FOR_ASSIGNMENT
            // Only ExecutiveManagerService can update the status to PICKED_FOR_ASSIGNMENT
            return;
        }
        updateInDataBase(deliveryExecutiveIdsList, executiveStatus);
        switch (executiveStatus) {
            case BUSY:
            case LEAVE:
                removeFromCache(deliveryExecutiveIdsList);
                break;
            default:
                break;
        }
        loadCache();
    }

    @Override
    public void addAvailableExecutivesForWorkAssignment(List<IExecutiveBean> executivesList) {
        boolean isDatabaseUsedAsOfNow = false; // currently we do not have any database and so we are storing all executives in cache only
        if (isDatabaseUsedAsOfNow) {
            //insertNewExecutivesInDatabase();
            loadCache();
        } else {
            // directly add all of these executives in cache
            List<ExecutiveBean> availableExecutivesListToBeAdded = new ArrayList<ExecutiveBean>();
            for (IExecutiveBean avilableExecutiveBeanToBeAdded : executivesList) {
                ExecutiveBean executiveBeanToBeAdded = new ExecutiveBean(avilableExecutiveBeanToBeAdded);
                availableExecutivesListToBeAdded.add(executiveBeanToBeAdded);
            }
            synchronized (availableExecutivesList) {
                availableExecutivesList = ExecutivePriorityManager.appendExecutivesListWithPriortization(
                        availableExecutivesList, availableExecutivesListToBeAdded);
            }
        }
    }

    private List<IExecutiveBean> cloneAvailableExecutivesList() {
        List<IExecutiveBean> availableExecutivesClonedList = new ArrayList<IExecutiveBean>();
        synchronized (availableExecutivesList) {
            for (ExecutiveBean avilableExecutiveBean : availableExecutivesList) {
                ExecutiveBean clonedExecutiveBean = new ExecutiveBean(avilableExecutiveBean);
                availableExecutivesClonedList.add(clonedExecutiveBean);
            }
        }
        return availableExecutivesClonedList;
    }

    private void removeFromCache(List<String> deliveryExecutiveIdsList) {
        if (deliveryExecutiveIdsList == null || deliveryExecutiveIdsList.isEmpty()) {
            // TODO: invalid operation. log and throw exception
            return;
        }
        synchronized (availableExecutivesList) {
            Iterator<ExecutiveBean> iterator = availableExecutivesList.iterator();
            while (iterator.hasNext()) {
                ExecutiveBean availableExecutiveBean = iterator.next();
                if (availableExecutiveBean.getExecutiveId() != null
                        && deliveryExecutiveIdsList.contains(availableExecutiveBean.getExecutiveId())) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Upadates all executive's status from 'PICKED_FOR_ASSIGNMENT' back to 'AVAILABLE'
     */
    private void recoverDatabase() {
        //        executeQuery("UPDATE delivery_executive_table SET status='AVAILABLE' WHERE status='PICKED_FOR_ASSIGNMENT'");
    }

    /**
     * Loads next set of available executives from database in application cache as per available cache size
     */
    private void loadCache() {
        //        int available_cache_size = 0;
        //        synchronized (availableExecutivesList) {
        //            available_cache_size = MAX_CACHE_SIZE_DEFAULT_CONFIG - availableExecutivesList.size();
        //        }
        //        List<ExecutiveBean> availableExecutivesListLoadedFromDatabase = executeQuery("SELECT * FROM delivery_executive_table WHERE status='AVAILABLE' ORDER BY last_order_delivered_time LIMIT available_cache_size");
        //        Update the status of availableExecutivesListLoadedFromDatabase to 'PICKED_FOR_ASSIGNMENT' in database
        //        synchronized (availableExecutivesList) {
        //            availableExecutivesList.addAll(availableExecutivesListLoadedFromDatabase);
        //        }
        //        Note : availableExecutivesList will always remain sorted on last_order_delivered_time, so we do not need to sort it again on application level
    }

    /**
     * updates the status of given executives in database 
     * @param deliveryExecutiveIdsList
     * @param executiveStatus
     */
    private void updateInDataBase(List<String> deliveryExecutiveIdsList, DeliveryExecutiveStatusEnum executiveStatus) {
        //TODO : updates the status of given executives in database 
    }

}
