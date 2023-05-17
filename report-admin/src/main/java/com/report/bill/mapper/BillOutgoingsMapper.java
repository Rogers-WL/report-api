package com.report.bill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.report.bill.domain.entity.BillOutgoingsDo;
import com.report.bill.domain.vo.outgoings.OutgoingsBaseInfoVo;

/**
 * @author: rogers
 */
public interface BillOutgoingsMapper extends BaseMapper<BillOutgoingsDo> {

    BillOutgoingsDo getSameBill(OutgoingsBaseInfoVo baseInfoVo);
}
