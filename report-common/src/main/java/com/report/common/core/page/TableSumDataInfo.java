package com.report.common.core.page;

import com.report.common.constant.HttpStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 表格分页数据对象
 *
 * @author rogers
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TableSumDataInfo extends TableDataInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal amountSum;


    public static TableSumDataInfo success(){
        TableSumDataInfo info = new TableSumDataInfo();
        info.setCode(HttpStatus.SUCCESS);
        info.setMsg("查询成功");
        return info;
    }
}
