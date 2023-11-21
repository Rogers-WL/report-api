package com.report.bill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.report.bill.domain.dto.OutgoingsDayDto;
import com.report.bill.domain.dto.OutgoingsQueryDto;
import com.report.bill.domain.dto.OutgoingsTagDto;
import com.report.bill.domain.entity.OutgoingsDo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rogers
 */
public interface BillOutgoingsMapper extends BaseMapper<OutgoingsDo> {

    List<OutgoingsDo> getList(OutgoingsQueryDto queryDto);

    BigDecimal getSumTotal(OutgoingsQueryDto queryDto);

    OutgoingsDo getSameBill(OutgoingsQueryDto queryDto);

    OutgoingsDo getMostSimilarBill(OutgoingsQueryDto queryDto);

    List<OutgoingsDayDto> getListByDay(OutgoingsQueryDto queryDto);

    List<OutgoingsTagDto> getListByType(OutgoingsQueryDto queryDto);

    List<OutgoingsTagDto> getListByTag(OutgoingsQueryDto queryDto);

    List<String> getDayList();
}
