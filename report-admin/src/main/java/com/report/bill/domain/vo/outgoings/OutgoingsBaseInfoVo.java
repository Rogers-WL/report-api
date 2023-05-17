package com.report.bill.domain.vo.outgoings;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: rogers
 */
@Data
public class OutgoingsBaseInfoVo {

    private BigDecimal amount;

    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime doTime;
}
