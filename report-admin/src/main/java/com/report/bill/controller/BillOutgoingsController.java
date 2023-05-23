package com.report.bill.controller;

import com.report.bill.domain.entity.BillOutgoingsDo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsListVo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsQueryVo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.OutgoingsBaseInfoVo;
import com.report.bill.mapstruct.BillOutgoingsMapstruct;
import com.report.bill.service.IBillOutgoingsService;
import com.report.common.core.controller.BaseController;
import com.report.common.core.domain.R;
import com.report.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: rogers
 */
@RestController
@RequestMapping("/bill/outgoings")
public class BillOutgoingsController extends BaseController {

    @Autowired
    private BillOutgoingsMapstruct mapstruct;

    @Autowired
    private IBillOutgoingsService service;

    @GetMapping("/list")
    public TableDataInfo list(BillOutgoingsQueryVo queryVo){
        startPage();
        List<BillOutgoingsDo> list = service.list(queryVo);
        List<BillOutgoingsListVo> voList = mapstruct.listDoToVo(list);
        return getDataTable(voList);
    }

    @PostMapping
    public R add(@RequestBody @Validated BillOutgoingsUpdateVo updateVo){
        service.add(updateVo);
        return R.success();
    }

    @PutMapping
    public R edit(@RequestBody @Validated BillOutgoingsUpdateVo updateVo){
        service.add(updateVo);
        return R.success();
    }

    @PostMapping("/predict")
    public R predictInput(@RequestBody OutgoingsBaseInfoVo baseInfoVo){
        return R.success(service.predictInput(baseInfoVo));
    }

}
