/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 26, 2017
 */
package com.pickup.order.assignment.handler.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class DataStructureModifierUtil {

    /**
     * Swaps the keys in Map of Map such that all the mappings remain exactly same
     * @param firstIdVsSecondIdVsValueMap
     * @return secondIdVsFirstIdVsValuesMap
     */
    public static Map<String, Map<String, Double>> swapKeysInTheMap(
            Map<String, Map<String, Double>> firstIdVsSecondIdVsValueMap) {
        Map<String, Map<String, Double>> secondIdVsFirstIdVsValuesMap = new HashMap<String, Map<String, Double>>();
        if (firstIdVsSecondIdVsValueMap != null && !firstIdVsSecondIdVsValueMap.isEmpty()) {
            for (Map.Entry<String, Map<String, Double>> firstIdVsSecondIdVsValuesEntry : firstIdVsSecondIdVsValueMap
                    .entrySet()) {
                String firstId = firstIdVsSecondIdVsValuesEntry.getKey();
                Map<String, Double> secondIdVsValuesMap = firstIdVsSecondIdVsValuesEntry.getValue();
                if (secondIdVsValuesMap != null && !secondIdVsValuesMap.isEmpty()) {
                    for (Map.Entry<String, Double> secondIdVsValuesMapEntry : secondIdVsValuesMap.entrySet()) {
                        String secondId = secondIdVsValuesMapEntry.getKey();
                        Double value = secondIdVsValuesMapEntry.getValue();

                        if (secondIdVsFirstIdVsValuesMap.containsKey(secondId)) {
                            Map<String, Double> firstIdVsValuesMap = secondIdVsFirstIdVsValuesMap.get(secondId);
                            firstIdVsValuesMap.put(firstId, value);
                        } else {
                            Map<String, Double> firstIdVsValuesMap = new HashMap<String, Double>();
                            firstIdVsValuesMap.put(firstId, value);
                            secondIdVsFirstIdVsValuesMap.put(secondId, firstIdVsValuesMap);
                        }
                    }
                }
            }
        }
        return secondIdVsFirstIdVsValuesMap;
    }
}
