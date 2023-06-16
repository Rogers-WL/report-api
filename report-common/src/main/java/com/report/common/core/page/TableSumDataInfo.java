package com.report.common.core.page;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 表格分页数据对象
 * 
 * @author rogers
 */
@Data
public class TableSumDataInfo extends TableDataInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private BigDecimal amountSum;

}
