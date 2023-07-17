package com.report.bill.domain.vo.outgoings.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class OutgoingsEditInfoVo {

    private Long id;

    private BigDecimal amount;

    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime doTime;

    private  String doTimeType;

    private String tag;

    private String remark;

    private Boolean isNecessary;
    
    private Set<String> tagOption;

}
