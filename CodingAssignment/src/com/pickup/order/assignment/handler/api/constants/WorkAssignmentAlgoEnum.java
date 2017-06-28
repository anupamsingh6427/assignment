/**
 * Copyright (c) 2017 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: anupam
 * Date:  Jun 25, 2017
 */
package com.pickup.order.assignment.handler.api.constants;

/**
 *
 */
public enum WorkAssignmentAlgoEnum {

    WORKFORCE_UTILIZATION_ORDER_ASSIGNMENT_ALGO("WORKFORCE_UTILIZATION_ORDER_ASSIGNMENT_ALGO"), //ensures maximum utilization of delivery boys
    ORDER_DELAY_OPTIMIZER_QUALITY_ENHANCER_ALGO("ORDER_DELAY_OPTIMIZER_QUALITY_ENHANCER_ALGO"), //ensures high priority orders are delivered first (Order_Priority: Order_delay + Customer_level) Customer_level includes things like premium customer or not
    SELF_DEDUCE_BEST_ALGORITHM_FOR_WORK_ASSIGNMENT("SELF_DEDUCE_BEST_ALGORITHM_FOR_WORK_ASSIGNMENT"); //tries to find optimal work assignment algorithm itself

    private String value;

    private WorkAssignmentAlgoEnum() {
        this.value = "";
    }

    private WorkAssignmentAlgoEnum(String stringValue) {
        this.value = stringValue;
    }

    public String getValue() {
        return this.value;
    }

}
