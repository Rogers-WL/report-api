package com.report.bill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.report.bill.domain.dto.OutgoingsDayDto;
import com.report.bill.domain.dto.OutgoingsTagDto;
import com.report.bill.domain.entity.OutgoingsDo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsPredictReqVo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsQueryVo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.resp.OutgoingsPredictVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author: rogers
 */
public interface IBillOutgoingsService extends IService<OutgoingsDo> {

    List<OutgoingsDo> list(OutgoingsQueryVo queryVo);

    BigDecimal getAmountSum(OutgoingsQueryVo queryVo);

    OutgoingsDo getDetail(Long id);

    void add(OutgoingsUpdateVo updateVo);

    void edit(OutgoingsUpdateVo updateVo);

    void del(Long[] ids);

    OutgoingsPredictVo predictTag(OutgoingsPredictReqVo reqVo);

    Set<String> getTagOptions(Integer type);

    List<OutgoingsDayDto> listByDay(OutgoingsQueryVo queryVo);

    List<OutgoingsTagDto> listByTag(OutgoingsQueryVo queryVo);
}
