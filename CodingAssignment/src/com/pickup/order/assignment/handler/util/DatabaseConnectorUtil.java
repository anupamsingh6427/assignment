/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.util;

/**
 * Uses database service and caches the saved preferences so that
 * preferences could be provided on runtime without db query
 */
public class DatabaseConnectorUtil {

    public static double fetchDatabaseConfigurationAsDoubleForKey(String key, Double defaultValue) {
        // TODO: anupam. [Jun 25, 2017 11:06:07 PM]. fetch configuration for this key from preference_service's cache or database
        // if there is no configuration then return default value
        return defaultValue;
    }

    public static int fetchDatabaseConfigurationAsIntegerForKey(String key, int defaultValue) {
        // TODO: anupam. [Jun 25, 2017 11:06:07 PM]. fetch configuration for this key from preference_service's cache or database
        // if there is no configuration then return default value
        return defaultValue;
    }

    public static String fetchDatabaseConfigurationAsStringForKey(String key, String defaultValue) {
        // TODO: anupam. [Jun 25, 2017 11:06:07 PM]. fetch configuration for this key from preference_service's cache or database
        // if there is no configuration then return default value
        return defaultValue;
    }
}
