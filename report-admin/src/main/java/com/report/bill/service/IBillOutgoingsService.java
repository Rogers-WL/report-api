package com.report.bill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.report.bill.domain.entity.BillOutgoingsDo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsQueryVo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.OutgoingsBaseInfoVo;
import com.report.bill.domain.vo.outgoings.OutgoingsPredictVo;

import java.util.List;

/**
 * @author: rogers
 */
public interface IBillOutgoingsService extends IService<BillOutgoingsDo> {

    List<BillOutgoingsDo> list(BillOutgoingsQueryVo queryVo);

    BillOutgoingsDo getDetail(Long id);

    void add(BillOutgoingsUpdateVo updateVo);

    void edit(BillOutgoingsUpdateVo updateVo);

    void del(Long[] ids);

    OutgoingsPredictVo predictInput(OutgoingsBaseInfoVo baseInfoVo);
}
