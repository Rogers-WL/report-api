package com.report.bill.domain.dto;

import com.report.bill.domain.bo.OutgoingStatisticsBo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rogers
 */
@Data
public class OutgoingsDayDto {

    private String day;

    private String weekday;

    private String doTimeType;

    private Integer times;

    private BigDecimal amount;

    private List<OutgoingStatisticsBo> details;

}
