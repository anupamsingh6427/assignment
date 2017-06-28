/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 25, 2017
 */
package com.pickup.order.assignment.handler.util;

/**
 * Controls logic behind distance calculation between any two points on earth
 * Used for calculating First Mile Distance in our system
 * It uses Haversine Formula for calculating the distance
 */
public class DistanceCalculatorUtil {

    /**
     * Calculates distance between any two points on earth
     * @param initialPointLatitude
     * @param intitialPointLongitude
     * @param finalPointLatitude
     * @param finalPointLongitude
     * @return distance between the points in meter
     */
    public static double calculateDistanceBetweenPoints(double initialPointLatitude, double intitialPointLongitude,
            double finalPointLatitude, double finalPointLongitude) {
        final int radius = 6371000; //radius of earth in meters
        double latitudeDistance = (finalPointLatitude - initialPointLatitude) * Math.PI / 180;
        double longitudeDistance = (finalPointLongitude - intitialPointLongitude) * Math.PI / 180;
        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + Math.cos(initialPointLatitude * Math.PI / 180) * Math.cos(finalPointLatitude * Math.PI / 180)
                * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        double angleBetweenBothThePoints = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * angleBetweenBothThePoints;
    }
}
