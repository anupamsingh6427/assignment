/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 27, 2017
 */
package com.pickup.order.assignment.handler.util;

/**
 *
 */
public class StringUtils {
    public static boolean isStringNullOrEmpty(String s) {
        return (s == null || s.trim().length() == 0);
    }
}
