package com.report.bill.domain.vo.outgoings.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: rogers
 */
@Data
public class OutgoingsTagVo {

    private Long id;

    private Long pid;

    private Integer times;

    private String tag;

    private BigDecimal amount;

    private Integer type;
}
