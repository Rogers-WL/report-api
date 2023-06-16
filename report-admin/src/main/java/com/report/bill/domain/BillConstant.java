package com.report.bill.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: rogers
 */
public class BillConstant {

    public static final String DAY_TYPE_WORKDAY = "workday";
    public static final String DAY_TYPE_WEEKEND = "weekend";
    public static final String DAY_TYPE_HOLIDAY = "holiday";
    public static final String DAY_TYPE_LEAVE = "leave";
    public static final List<String> DAY_TYPE_OPTION = new ArrayList<String>() {
        {
            add(DAY_TYPE_WORKDAY);
            add(DAY_TYPE_WEEKEND);
            add(DAY_TYPE_HOLIDAY);
            add(DAY_TYPE_LEAVE);
        }
    };

    public static final String OUTGOINGS_TYPE_DICT = "bill_outgoings_type";

    public static final int OUTGOINGS_TYPE_REPAST = 0;
    public static final int OUTGOINGS_TYPE_CAR = 1;
    public static final int OUTGOINGS_TYPE_SHOPPING = 2;
    public static final int OUTGOINGS_TYPE_DAILY = 3;
    public static final int OUTGOINGS_TYPE_OTHER = 4;


}
