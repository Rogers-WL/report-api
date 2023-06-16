package com.report.bill.domain.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class OutgoingStatisticsBo {

    private BigDecimal amount;

    private String name;

}
