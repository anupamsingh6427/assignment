/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 25, 2017
 */
package com.pickup.order.assignment.handler.api.service;

import java.util.List;

import com.pickup.order.assignment.handler.api.constants.DeliveryExecutiveStatusEnum;
import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;

/**
 * Manages all available delivery executives
 * (keeps them in sorted order of waiting time for ease and simplicity)
 */
public interface IExecutiveManagerService {

    /**
     * Provides list of available executives sorted according
     * to their waiting time
     * @return list of available delivery executives
     */
    public List<IExecutiveBean> getAllAvailableExecutives();

    /**
     * Updates delivery executive's status in database
     * Removes executives (who are made unavailable) from cache
     * Loads next set of available executives for work assignment in Application Cache as per cache limit
     * @param deliveryExecutiveIdsList
     * @param executivesStatusToBeUpdated
     */
    public void updateDeliveryExecutivesStatus(List<String> deliveryExecutiveIdsList,
            DeliveryExecutiveStatusEnum executiveStatus);

    /**
     * Adds the list of new available executives in database to whom orders will be assigned when required
     * Also updates this list in application cache if cache size permits the same
     */
    public void addAvailableExecutivesForWorkAssignment(List<IExecutiveBean> executivesList);

}
