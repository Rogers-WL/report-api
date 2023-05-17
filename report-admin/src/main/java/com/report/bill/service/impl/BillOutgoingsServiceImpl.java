package com.report.bill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.report.bill.domain.entity.BillOutgoingsDo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.OutgoingsBaseInfoVo;
import com.report.bill.mapper.BillOutgoingsMapper;
import com.report.bill.mapstruct.BillOutgoingsMapstruct;
import com.report.bill.service.IBillOutgoingsService;
import com.report.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;


/**
 * @Author: rogers
 */
@Service
public class BillOutgoingsServiceImpl extends ServiceImpl<BillOutgoingsMapper, BillOutgoingsDo> implements IBillOutgoingsService {

    @Autowired
    private BillOutgoingsMapstruct mapstruct;

    @Autowired
    private BillOutgoingsMapper mapper;

    @Override
    public R add(BillOutgoingsUpdateVo updateVo) {
        BillOutgoingsDo outgoingsDo = mapstruct.updatVoToDo(updateVo);
        save(outgoingsDo);
        return R.success();
    }

    @Override
    public R predictInput(OutgoingsBaseInfoVo baseInfoVo) {

        DayOfWeek dayOfWeek = baseInfoVo.getDoTime().getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {

        }

        // 精确匹配

        return null;
    }
}
