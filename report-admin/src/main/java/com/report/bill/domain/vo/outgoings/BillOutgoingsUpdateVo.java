package com.report.bill.domain.vo.outgoings;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: rogers
 */
@Data
public class BillOutgoingsUpdateVo {

    @NotNull(message = "金额必填")
    private BigDecimal amount;

    @NotNull(message = "分类必选")
    private Integer type;

    private String typeName;

    @NotNull(message = "消费时间必填")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime doTime;

    private  String doTimeType;

    private String tag;

    private String remark;

    private Boolean isNecessary;
}
