/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 27, 2017
 */
package com.pickup.order.assignment.handler.init;

import java.util.ArrayList;
import java.util.List;

import com.pickup.order.assignment.handler.algorithm.OrderDelayOptimizerAssignmentAlgorithm;
import com.pickup.order.assignment.handler.algorithm.SelfDeducedOptimalAssignmentAlgorithm;
import com.pickup.order.assignment.handler.algorithm.WorkforceUtilizationOrderAssignmentAlgorithm;
import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.api.entities.IRestaurantBean;
import com.pickup.order.assignment.handler.api.entities.ITaskAssignmentAlgorithm;
import com.pickup.order.assignment.handler.api.service.IExecutiveManagerService;
import com.pickup.order.assignment.handler.api.service.IOrderAssignmentTimerService;
import com.pickup.order.assignment.handler.api.service.IOrderManagerService;
import com.pickup.order.assignment.handler.api.service.IRestaurantManagerService;
import com.pickup.order.assignment.handler.api.service.ITaskAssignmentAlgoManagerService;
import com.pickup.order.assignment.handler.bean.RestaurantBean;
import com.pickup.order.assignment.handler.impl.service.ExecutiveManagerService;
import com.pickup.order.assignment.handler.impl.service.OrderAssignmentTimerService;
import com.pickup.order.assignment.handler.impl.service.OrderManagerService;
import com.pickup.order.assignment.handler.impl.service.RestaurantManagerService;
import com.pickup.order.assignment.handler.impl.service.TaskAssignmentAlgoManagerService;

/**
 *
 */
public class ProgramLauncher {

    public static void main(String[] args) {
        initializeSystem();

        // take current snapshot as input (snapshot of pending orders and available executives)
        List<IOrderBean> ordersInputList = ProgramInputProvider.readPendingOrdersListSnapshotAsInput();
        List<IExecutiveBean> availableExecutivesList = ProgramInputProvider.readAvailableExecutivesSnapshotAsInput();
        passOnThisCurrentSnapshotAsInputToTheSystem(ordersInputList, availableExecutivesList);

        startPendingOrderAssignmentsToAvailableExecutives();
    }

    private static void initializeSystem() {
        registerAlgos();
        registerRestaurants();
    }

    private static void registerAlgos() {
        ITaskAssignmentAlgorithm orderDelayOptimizerAssignmentAlgorithm = new OrderDelayOptimizerAssignmentAlgorithm();
        ITaskAssignmentAlgorithm workforceUtilizationOrderAssignmentAlgorithm = new WorkforceUtilizationOrderAssignmentAlgorithm();
        ITaskAssignmentAlgorithm selfDeducedOptimalAssignmentAlgorithm = new SelfDeducedOptimalAssignmentAlgorithm();

        ITaskAssignmentAlgoManagerService taskAssignmentAlgoManagerService = TaskAssignmentAlgoManagerService
                .getInstance();
        taskAssignmentAlgoManagerService.registerNewAlgorithmForTaskAssignment(orderDelayOptimizerAssignmentAlgorithm);
        taskAssignmentAlgoManagerService
                .registerNewAlgorithmForTaskAssignment(workforceUtilizationOrderAssignmentAlgorithm);
        taskAssignmentAlgoManagerService.registerNewAlgorithmForTaskAssignment(selfDeducedOptimalAssignmentAlgorithm);
    }

    private static void registerRestaurants() {
        List<IRestaurantBean> restaurantListToBeAdded = new ArrayList<IRestaurantBean>();
        RestaurantBean firstRestaurantBean = new RestaurantBean(null, "HALDIRAM", 28.6003951, 77.2032016);
        restaurantListToBeAdded.add(firstRestaurantBean);
        RestaurantBean secondRestaurantBean = new RestaurantBean(null, "APPU GHAR", 28.4632737, 77.0705737);
        restaurantListToBeAdded.add(secondRestaurantBean);
        RestaurantBean thirdRestaurantBean = new RestaurantBean(null, "DOMINOS", 28.4124986, 77.0436614);
        restaurantListToBeAdded.add(thirdRestaurantBean);
        IRestaurantManagerService restaurantManagerService = RestaurantManagerService.getInstance();
        restaurantManagerService.addRestaurantList(restaurantListToBeAdded);
    }

    private static void passOnThisCurrentSnapshotAsInputToTheSystem(List<IOrderBean> ordersInputList,
            List<IExecutiveBean> availableExecutivesList) {
        IOrderManagerService orderManagerSevice = OrderManagerService.getInstance();
        orderManagerSevice.addNewOrdersForAssignment(ordersInputList);

        IExecutiveManagerService executiveManagerService = ExecutiveManagerService.getInstance();
        executiveManagerService.addAvailableExecutivesForWorkAssignment(availableExecutivesList);
    }

    private static void startPendingOrderAssignmentsToAvailableExecutives() {
        IOrderAssignmentTimerService orderAssignmentTimerService = OrderAssignmentTimerService.getInstance();
        orderAssignmentTimerService.startOrderAssignmentToExecutives();
    }

}
