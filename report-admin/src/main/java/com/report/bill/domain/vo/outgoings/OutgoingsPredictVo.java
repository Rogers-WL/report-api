package com.report.bill.domain.vo.outgoings;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

/**
 * @author: rogers
 */
@Data
@Accessors(chain = true)
public class OutgoingsPredictVo {

    private String doTimeType;

    private List<String> doTimeTypeOption;

    private List<String> tag;

    private Set<String > tagOption;

    private Boolean isNecessary;
}
