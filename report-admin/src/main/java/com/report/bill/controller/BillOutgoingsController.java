package com.report.bill.controller;

import com.github.pagehelper.PageInfo;
import com.report.bill.domain.dto.OutgoingsDayDto;
import com.report.bill.domain.dto.OutgoingsEditInfoDto;
import com.report.bill.domain.dto.OutgoingsTagDto;
import com.report.bill.domain.entity.OutgoingsDo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsPredictReqVo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsQueryVo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.resp.OutgoingsDayVo;
import com.report.bill.domain.vo.outgoings.resp.OutgoingsVo;
import com.report.bill.mapstruct.BillOutgoingsMapstruct;
import com.report.bill.service.IBillOutgoingsService;
import com.report.common.core.controller.BaseController;
import com.report.common.core.domain.R;
import com.report.common.core.page.TableDataInfo;
import com.report.common.core.page.TableSumDataInfo;
import com.report.common.exception.BusinessException;
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
    public TableDataInfo list(OutgoingsQueryVo queryVo) {
        startPage();
        List<OutgoingsDo> list = service.list(queryVo);
        List<OutgoingsVo> voList = mapstruct.listDoToVo(list);

        TableSumDataInfo rspData =  TableSumDataInfo.success();
        rspData.setRows(voList);
        rspData.setTotal(new PageInfo(list).getTotal());
        rspData.setAmountSum(service.getAmountSum(queryVo));
        return rspData;
    }

    @GetMapping("/list/day")
    public TableDataInfo listByDay(OutgoingsQueryVo queryVo) {
        startPage();
        List<OutgoingsDayDto> list = service.listByDay(queryVo);
        List<OutgoingsDayVo> voList = mapstruct.listDayDtoToVo(list);

        TableSumDataInfo rspData = TableSumDataInfo.success();
        rspData.setRows(voList);
        rspData.setTotal(new PageInfo(list).getTotal());
        rspData.setAmountSum(service.getAmountSum(queryVo));
        return rspData;
    }

    @GetMapping("/list/tag")
    public R listByTag(OutgoingsQueryVo queryVo) {
        List<OutgoingsTagDto> list = service.listByTag(queryVo);
        return R.success(mapstruct.listTagDtoToVo(list));
    }

    @GetMapping("/detail/{id}")
    public R getDetail(@PathVariable Long id) {
        OutgoingsDo outgoingsDo = service.getDetail(id);
        return R.success(mapstruct.doToVo(outgoingsDo));
    }

    @PostMapping
    public R add(@RequestBody @Validated OutgoingsUpdateVo updateVo) {
        service.add(updateVo);
        return R.success();
    }

    @GetMapping("/edit/{id}")
    public R getEditInfo(@PathVariable Long id) {
        OutgoingsEditInfoDto dto = service.getEditInfo(id);
        return R.success(mapstruct.editInfoDtoToVo(dto));
    }

    @PutMapping
    public R edit(@RequestBody @Validated OutgoingsUpdateVo updateVo) {
        if (updateVo.getId() == null) {
            throw new BusinessException("id不能为空");
        }
        service.edit(updateVo);
        return R.success();
    }

    @DeleteMapping("/{ids}")
    public R del(@PathVariable Long[] ids) {
        service.del(ids);
        return R.success();
    }

    @PostMapping("/predict")
    public R predictTag(@RequestBody OutgoingsPredictReqVo reqVo) {
        return R.success(service.predictTag(reqVo));
    }

    @GetMapping("/options/tag/{type}")
    public R getTagOptions(@PathVariable List<Integer> type) {
        return R.success(service.getTagOptions(type));
    }

}
