package com.report.bill.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author: rogers
 */
@Data
@Accessors(chain = true)
public class OutgoingsEditInfoDto {

    private Long id;

    private BigDecimal amount;

    private Integer type;

    private LocalDateTime doTime;

    private String doTimeType;

    private String tag;

    private String remark;

    private Boolean isNecessary;

    private Set<String> tagOption;

}
