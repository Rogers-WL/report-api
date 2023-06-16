package com.report.bill.domain.vo.outgoings.resp;

import com.report.bill.domain.bo.OutgoingStatisticsBo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rogers
 */
@Data
public class OutgoingsDayVo {

    private String day;

    private String weekday;

    private String doTimeType;

    private Integer times;

    private BigDecimal amount;

    private List<OutgoingStatisticsBo> details;

}
