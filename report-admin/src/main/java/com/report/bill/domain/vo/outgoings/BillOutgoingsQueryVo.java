package com.report.bill.domain.vo.outgoings;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: rogers
 */
@Data
public class BillOutgoingsQueryVo {

    private BigDecimal amount;

    private Integer type;

    private LocalDateTime doTime;

    private  String doTimeType;

    private String tag;

    private String remark;

    private Boolean isNecessary;
}
