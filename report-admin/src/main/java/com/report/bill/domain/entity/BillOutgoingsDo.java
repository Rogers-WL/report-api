package com.report.bill.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.report.common.core.domain.BaseDo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: rogers
 */
@Data
@TableName("bill_outgoings")
public class BillOutgoingsDo extends BaseDo {

    private BigDecimal amount;

    private Integer type;

    private String typeName;

    private LocalDateTime doTime;

    private String doTimeType;

    private String tag;

    private String remark;

    private Boolean isNecessary;
}
