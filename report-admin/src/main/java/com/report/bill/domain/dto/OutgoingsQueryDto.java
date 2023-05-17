package com.report.bill.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: rogers
 */
@Data
public class OutgoingsQueryDto {

    private BigDecimal amount;

    private Integer type;

    private String time;

    private String doTimeType;
}
