package com.report.bill.domain.vo.outgoings.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: rogers
 */
@Data
public class OutgoingsQueryVo {

    private Integer type;

    private String tag;

    private String key;

    private Boolean isNecessary;

    private String timeStart;

    private String timeEnd;

    private BigDecimal amountLow;

    private BigDecimal amountHigh;


}
