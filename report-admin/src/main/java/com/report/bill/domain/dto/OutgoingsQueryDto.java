package com.report.bill.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author: rogers
 */
@Data
@Accessors(chain = true)
public class OutgoingsQueryDto {

    private BigDecimal amount;

    private Integer type;

    private String doTime;

    private String doTimeType;

    private String timeStart;

    private String timeEnd;

    private BigDecimal amountLow;

    private BigDecimal amountHigh;

    private String tag;

    private String key;

    private Boolean isNecessary;

}
