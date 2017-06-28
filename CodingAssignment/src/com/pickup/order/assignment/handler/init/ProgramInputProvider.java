/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 27, 2017
 */
package com.pickup.order.assignment.handler.init;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pickup.order.assignment.handler.api.constants.DeliveryExecutiveStatusEnum;
import com.pickup.order.assignment.handler.api.constants.OrderStatusEnum;
import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.bean.ExecutiveBean;
import com.pickup.order.assignment.handler.bean.OrderBean;

/**
 * Provides input snapshot to the Program Launcher
 */
public class ProgramInputProvider {

    public static List<IOrderBean> readPendingOrdersListSnapshotAsInput() {
        List<IOrderBean> inputOrdersList = new ArrayList<IOrderBean>();
        OrderBean firstOrderBean = new OrderBean("1", new Date(System.currentTimeMillis() - 180000), "1", "1",
                OrderStatusEnum.PENDING);
        inputOrdersList.add(firstOrderBean);
        OrderBean secondOrderBean = new OrderBean("2", new Date(System.currentTimeMillis() - 120000), "2", "2",
                OrderStatusEnum.PENDING);
        inputOrdersList.add(secondOrderBean);
        OrderBean thirdOrderBean = new OrderBean("3", new Date(System.currentTimeMillis() - 60000), "3", "3",
                OrderStatusEnum.PENDING);
        inputOrdersList.add(thirdOrderBean);
        return inputOrdersList;
    }

    public static List<IExecutiveBean> readAvailableExecutivesSnapshotAsInput() {
        List<IExecutiveBean> availableExecutivesInputList = new ArrayList<IExecutiveBean>();
        ExecutiveBean firstExecutiveBean = new ExecutiveBean("1", "Ashok", "Kumar", "8800737780", "ashok@gmail.com",
                DeliveryExecutiveStatusEnum.AVAILABLE, new Date(System.currentTimeMillis() - 10800000), 28.4121778,
                77.0580193);
        availableExecutivesInputList.add(firstExecutiveBean);
        ExecutiveBean secondExecutiveBean = new ExecutiveBean("2", "Sagar", "Mahajan", "9999083609", "sagar@gmail.com",
                DeliveryExecutiveStatusEnum.AVAILABLE, new Date(System.currentTimeMillis() - 7200000), 28.5988373,
                77.2117464);
        availableExecutivesInputList.add(secondExecutiveBean);
        ExecutiveBean thirdExecutiveBean = new ExecutiveBean("3", "Sonu", "Singh", "7668248895", "sonu@gmail.com",
                DeliveryExecutiveStatusEnum.AVAILABLE, new Date(System.currentTimeMillis() - 3600000), 28.4705399,
                77.0711296);
        availableExecutivesInputList.add(thirdExecutiveBean);
        return availableExecutivesInputList;
    }

}
