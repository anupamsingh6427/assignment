/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.impl.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.bean.ExecutiveBean;

/**
 *  Decides priority of the executives to be picked first for assignment
 */
public class ExecutivePriorityManager {

    /**
     *  Re-priortizes the available executives list
     *  Based on last_order_delivered_time (executive_waiting_time)
     */
    public static List<ExecutiveBean> appendExecutivesListWithPriortization(
            List<ExecutiveBean> existingAvailableExecutives, List<ExecutiveBean> newAvailableExecutivesToBeAppended) {
        List<ExecutiveBean> finalAppendedPriortizedAvailableExecutivesList = new ArrayList<ExecutiveBean>();
        finalAppendedPriortizedAvailableExecutivesList.addAll(existingAvailableExecutives);
        finalAppendedPriortizedAvailableExecutivesList.addAll(newAvailableExecutivesToBeAppended);
        Collections.sort(finalAppendedPriortizedAvailableExecutivesList, new Comparator<IExecutiveBean>() {

            @Override
            public int compare(IExecutiveBean executiveBean1, IExecutiveBean executiveBean2) {
                return executiveBean1.getLastOrderDeliveredTime().compareTo(executiveBean2.getLastOrderDeliveredTime());
            }
        });
        return finalAppendedPriortizedAvailableExecutivesList;
    }
}
