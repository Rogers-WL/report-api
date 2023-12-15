package com.report.bill.controller;

import com.report.bill.mapstruct.BillOutgoingsMapstruct;
import com.report.bill.service.IBillStatisticsService;
import com.report.common.core.controller.BaseController;
import com.report.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: rogers
 */
@RestController
@RequestMapping("/bill/statistics")
public class BillStatisticsController extends BaseController {

    @Autowired
    private BillOutgoingsMapstruct mapstruct;

    @Autowired
    private IBillStatisticsService service;

    @GetMapping("/overview")
    public R getOverview(){
            return R.success();
    }

    @GetMapping("/out/{year}")
    public R getOutgoing(@PathVariable Integer year) {

        return R.success();
    }

}
