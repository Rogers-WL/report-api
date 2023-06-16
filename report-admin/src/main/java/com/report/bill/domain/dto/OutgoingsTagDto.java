package com.report.bill.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: rogers
 */
@Data
public class OutgoingsTagDto {

    private Integer id;

    private Integer pid;

    private String tag;

    private Integer times;

    private BigDecimal amount;

    private Integer type;

}
