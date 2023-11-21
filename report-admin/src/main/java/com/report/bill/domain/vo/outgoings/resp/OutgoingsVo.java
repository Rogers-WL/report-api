package com.report.bill.domain.vo.outgoings.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: rogers
 */
@Data
public class OutgoingsVo {

    private Long id;

    private BigDecimal amount;

    private String weekday;

    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime doDate;

    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private LocalDateTime doTime;

    private String doTimeType;

    private String tag;

    private String remark;

    private Boolean isNecessary;
}
