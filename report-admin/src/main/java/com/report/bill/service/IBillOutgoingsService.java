package com.report.bill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.report.bill.domain.entity.BillOutgoingsDo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.OutgoingsBaseInfoVo;
import com.report.common.core.domain.R;

/**
 * @author: rogers
 */
public interface IBillOutgoingsService extends IService<BillOutgoingsDo> {

    R add(BillOutgoingsUpdateVo updateVo);

    R predictInput(OutgoingsBaseInfoVo baseInfoVo);
}
