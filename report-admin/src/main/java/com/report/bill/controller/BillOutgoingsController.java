package com.report.bill.controller;

import com.report.bill.domain.vo.outgoings.BillOutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.OutgoingsBaseInfoVo;
import com.report.bill.service.IBillOutgoingsService;
import com.report.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: rogers
 */
@RestController
@RequestMapping("/bill/outgoings")
public class BillOutgoingsController {

    @Autowired
    private IBillOutgoingsService service;

    @GetMapping
    public R list(){
        return R.success();
    }

    @PostMapping
    public R add(@RequestBody @Validated BillOutgoingsUpdateVo updateVo){
        return service.add(updateVo);
    }

    @PostMapping("/predict")
    public R predictInput(@RequestBody OutgoingsBaseInfoVo baseInfoVo){
        return service.predictInput(baseInfoVo);
    }

}
