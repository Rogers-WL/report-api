package com.report.bill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.report.bill.domain.dto.OutgoingsQueryDto;
import com.report.bill.domain.entity.BillOutgoingsDo;

/**
 * @author: rogers
 */
public interface BillOutgoingsMapper extends BaseMapper<BillOutgoingsDo> {

    BillOutgoingsDo getSameBill(OutgoingsQueryDto queryDto);

    BillOutgoingsDo getMostSimilarBill(OutgoingsQueryDto queryDto);

}
