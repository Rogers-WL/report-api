package com.report.bill.domain.vo.outgoings;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: rogers
 */
@Data
@TableName("bill_outgoings")
public class BillOutgoingsListVo {

    private Long id;

    private BigDecimal amount;

    private String weekday;

    private Integer type;

    private String typeName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime doTime;

    private String doTimeType;

    private String tag;

    private String remark;

    private Boolean isNecessary;
}
