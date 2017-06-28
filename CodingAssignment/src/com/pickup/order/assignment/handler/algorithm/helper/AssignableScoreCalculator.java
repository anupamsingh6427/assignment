/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 25, 2017
 */
package com.pickup.order.assignment.handler.algorithm.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pickup.order.assignment.handler.api.entities.IExecutiveBean;
import com.pickup.order.assignment.handler.api.entities.IOrderBean;
import com.pickup.order.assignment.handler.api.entities.IRestaurantBean;
import com.pickup.order.assignment.handler.api.service.IRestaurantManagerService;
import com.pickup.order.assignment.handler.constants.DatabaseConstants;
import com.pickup.order.assignment.handler.impl.service.RestaurantManagerService;
import com.pickup.order.assignment.handler.util.DatabaseConnectorUtil;
import com.pickup.order.assignment.handler.util.DateTimeUtil;
import com.pickup.order.assignment.handler.util.DistanceCalculatorUtil;

/**
 * Logic behind overall assignable score calculation for a order assignment to an executive
 * Assignable score includes weighted incorporation of all the factors responsible behind assignment logic
 * Responsible factors include First_mile_distance, DE_waiting_time, Order_delay_time etc
 */
public class AssignableScoreCalculator {

    final static int MAX_POSSIBLE_ORDER_DELAY_TIME_DEFAULT_CONFIG = 180; // in minutes
    final static int MAX_POSSIBLE_EXECUTIVE_WAITING_TIME_DEFAULT_CONFIG = 480; // in minutes 
    final static int MAX_POSSIBLE_FIRST_MILE_DISTANCE_DEFAULT_CONFIG = 40000; // in meters 

    final static double ORDER_DELAY_TIME_DEFAULT_WEIGHTAGE = 0.2;
    final static double FIRST_MILE_DISTANCE_DEFAULT_WEIGHTAGE = 0.7;
    final static double EXECUTIVE_WAITING_TIME_DEFAULT_WEIGHTAGE = 0.1;

    /**
     * Provides AssignableScore for each orderVsExecutive mapping
     * @param orderList
     * @param availableExecutivesList
     * @return orderIdVsExecutiveIdVsAssignableScoreMap
     */
    public static Map<String, Map<String, Double>> calculateAssignableScoreForTheseOrdersAndAvailableExecutives(
            List<IOrderBean> orderList, List<IExecutiveBean> availableExecutivesList) {
        Map<String, Map<String, Double>> orderIdVsExecutiveIdVsValueMap = new HashMap<String, Map<String, Double>>();
        for (IOrderBean orderBean : orderList) {
            Map<String, Double> executiveIdVsValueMap = new HashMap<String, Double>();
            for (IExecutiveBean executiveBean : availableExecutivesList) {
                double assignableScoreValue = calculateAssignableScoreValueForGivenOrderBeanToThisExecutive(orderBean,
                        executiveBean);
                executiveIdVsValueMap.put(executiveBean.getExecutiveId(), assignableScoreValue);
            }
            orderIdVsExecutiveIdVsValueMap.put(orderBean.getOrderId(), executiveIdVsValueMap);
        }
        return orderIdVsExecutiveIdVsValueMap;
    }

    /**
     * Calculates assignable score value (lies between 0 to 100) using three factors
     * Order_delay_time, Executive_waiting_time and First_mile_distance
     * @param orderBean
     * @param executiveBean
     * @return assignable score value (0 to 100)
     */
    private static double calculateAssignableScoreValueForGivenOrderBeanToThisExecutive(IOrderBean orderBean,
            IExecutiveBean executiveBean) {
        double orderDelayTime = DateTimeUtil.calculateDurationFrom(orderBean.getOrderTime());
        double executiveWaitingTime = DateTimeUtil.calculateDurationFrom(executiveBean.getLastOrderDeliveredTime());
        double firstMileDistance = DistanceCalculatorUtil.calculateDistanceBetweenPoints(
                executiveBean.getExecutiveLocationsLatitude(), executiveBean.getExecutiveLocationsLongitude(),
                getRestaurant(orderBean.getAssociatedRestaurantId()).getRestaurantLocationsLatitude(),
                getRestaurant(orderBean.getAssociatedRestaurantId()).getRestaurantLocationsLongitude());

        int maxPossibleOrderDelayTimeConfig = DatabaseConnectorUtil.fetchDatabaseConfigurationAsIntegerForKey(
                DatabaseConstants.MAX_POSSIBLE_ORDER_DELAY_TIME, MAX_POSSIBLE_ORDER_DELAY_TIME_DEFAULT_CONFIG);
        int maxPossibleExecutiveWaitingTimeConfig = DatabaseConnectorUtil.fetchDatabaseConfigurationAsIntegerForKey(
                DatabaseConstants.MAX_POSSIBLE_EXECUTIVE_WAITING_TIME,
                MAX_POSSIBLE_EXECUTIVE_WAITING_TIME_DEFAULT_CONFIG);
        int maxPossibleFirstMileDistanceConfig = DatabaseConnectorUtil.fetchDatabaseConfigurationAsIntegerForKey(
                DatabaseConstants.MAX_POSSIBLE_FIRST_MILE_DISTANCE, MAX_POSSIBLE_FIRST_MILE_DISTANCE_DEFAULT_CONFIG);

        double orderDelayTimeIndex = (orderDelayTime / maxPossibleOrderDelayTimeConfig) * 100; //range : 0 to 100
        double executiveWaitingTimeIndex = (executiveWaitingTime / maxPossibleExecutiveWaitingTimeConfig) * 100; //range : 0 to 100
        double firstMileDistanceIndex = (firstMileDistance / maxPossibleFirstMileDistanceConfig) * 100; //range : 0 to 100

        return calculatedOverallWeightedAssignableScoreConsideringTheseFactors(orderDelayTimeIndex,
                executiveWaitingTimeIndex, firstMileDistanceIndex);
    }

    /**
     * Calculates overall assignable score after incorporating configured weight for each factor
     * @param orderDelayTimeIndex (range 0 to 100)
     * @param executiveWaitingTimeIndex (range 0 to 100)
     * @param firstMileDistanceIndex (range 0 to 100)
     * @return finalAssignableScore of an order to a delivery executive (range 0 to 100)
     */
    private static double calculatedOverallWeightedAssignableScoreConsideringTheseFactors(double orderDelayTimeIndex,
            double executiveWaitingTimeIndex, double firstMileDistanceIndex) {
        double orderDelayTimeWeightConfig = DatabaseConnectorUtil.fetchDatabaseConfigurationAsDoubleForKey(
                DatabaseConstants.ORDER_DELAY_TIME_WEIGHTAGE, ORDER_DELAY_TIME_DEFAULT_WEIGHTAGE);
        double executiveWaitingTimeWeightConfig = DatabaseConnectorUtil.fetchDatabaseConfigurationAsDoubleForKey(
                DatabaseConstants.EXECUTIVE_WAITING_TIME_WEIGHTAGE, EXECUTIVE_WAITING_TIME_DEFAULT_WEIGHTAGE);
        double firstMileDistanceWeightConfig = DatabaseConnectorUtil.fetchDatabaseConfigurationAsDoubleForKey(
                DatabaseConstants.FIRST_MILE_DISTANCE_WEIGHTAGE, FIRST_MILE_DISTANCE_DEFAULT_WEIGHTAGE);

        double sumOfAllWeights = orderDelayTimeWeightConfig + executiveWaitingTimeWeightConfig
                + firstMileDistanceWeightConfig;

        if (sumOfAllWeights != 1) {
            // invalid weights provided, use default weights
            orderDelayTimeWeightConfig = ORDER_DELAY_TIME_DEFAULT_WEIGHTAGE;
            executiveWaitingTimeWeightConfig = EXECUTIVE_WAITING_TIME_DEFAULT_WEIGHTAGE;
            firstMileDistanceWeightConfig = FIRST_MILE_DISTANCE_DEFAULT_WEIGHTAGE;
        }
        double overallAssignableScore = orderDelayTimeIndex * orderDelayTimeWeightConfig + executiveWaitingTimeIndex
                * executiveWaitingTimeWeightConfig + (100 - firstMileDistanceIndex) * firstMileDistanceWeightConfig;
        return overallAssignableScore;
    }

    private static IRestaurantBean getRestaurant(String restaurantId) {
        IRestaurantManagerService restaurantManager = RestaurantManagerService.getInstance();
        IRestaurantBean restaurantBean = restaurantManager.getRestaurantById(restaurantId);
        return restaurantBean;
    }
}
