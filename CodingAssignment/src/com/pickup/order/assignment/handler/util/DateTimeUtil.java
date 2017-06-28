/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.util;

import java.util.Date;

/**
 * Responsible for performing operations on date-time
 */
public class DateTimeUtil {

    /**
     * Provides duration from given time to current time in minutes
     * @param timestamp
     * @return duration in minutes
     */
    public static double calculateDurationFrom(Date timestamp) {
        return (System.currentTimeMillis() - timestamp.getTime()) / (60 * 1000);
    }

}
