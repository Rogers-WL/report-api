package com.report.bill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.report.bill.domain.BillConstant;
import com.report.bill.domain.dto.OutgoingsQueryDto;
import com.report.bill.domain.entity.BillOutgoingsDo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsQueryVo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.OutgoingsBaseInfoVo;
import com.report.bill.domain.vo.outgoings.OutgoingsPredictVo;
import com.report.bill.mapper.BillOutgoingsMapper;
import com.report.bill.mapstruct.BillOutgoingsMapstruct;
import com.report.bill.service.IBillOutgoingsService;
import com.report.common.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.*;


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
    public List<BillOutgoingsDo> list(BillOutgoingsQueryVo queryVo) {
        return list(new LambdaQueryWrapper<BillOutgoingsDo>().eq(queryVo.getType() != null, BillOutgoingsDo::getType, queryVo.getType())
                .orderByDesc(BillOutgoingsDo::getDoTime));
    }

    @Override
    public void add(BillOutgoingsUpdateVo updateVo) {
        BillOutgoingsDo outgoingsDo = mapstruct.updateVoToDo(updateVo);
        String dayOfWeek = updateVo.getDoTime().getDayOfWeek().name();
        outgoingsDo.setTag(String.join(",", updateVo.getTag()))
                .setTypeName(DictUtils.getDictLabel(BillConstant.OUTGOINGS_TYPE_DICT, updateVo.getType().toString()))
                .setWeekday(dayOfWeek.charAt(0)  + dayOfWeek.toLowerCase().substring(1).toLowerCase());
        save(outgoingsDo);
    }

    @Override
    public void edit(BillOutgoingsUpdateVo updateVo) {

    }

    @Override
    public OutgoingsPredictVo predictInput(OutgoingsBaseInfoVo baseInfoVo) {
        String doTimeType = BillConstant.DAY_TYPE_WORKDAY;
        DayOfWeek dayOfWeek = baseInfoVo.getDoTime().getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            doTimeType = BillConstant.DAY_TYPE_WEEKEND;
        }
        // 精确匹配
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        OutgoingsQueryDto queryDto = mapstruct.baseVoToQueryDto(baseInfoVo);
        queryDto.setDoTimeType(doTimeType)
                .setTime(baseInfoVo.getDoTime().format(timeFormatter));
        BillOutgoingsDo sameBill = mapper.getSameBill(queryDto);
        if (sameBill == null) {
            BigDecimal amountFloat = BigDecimal.valueOf(50);
            int timeFloat = 2;
            queryDto.setAmountLow(baseInfoVo.getAmount().subtract(amountFloat))
                    .setAmouontHigh(baseInfoVo.getAmount().add(amountFloat))
                    .setTimeStart(baseInfoVo.getDoTime().minusHours(timeFloat).format(timeFormatter))
                    .setTimeEnd(baseInfoVo.getDoTime().plusHours(timeFloat).format(timeFormatter));
            sameBill = mapper.getMostSimilarBill(queryDto);
            if (sameBill == null) {
                queryDto.setAmountLow(null)
                        .setAmouontHigh(null);
                sameBill = mapper.getMostSimilarBill(queryDto);
            }
        }

        List<Object> tagList = listObjs(new QueryWrapper<BillOutgoingsDo>().select("distinct tag")
                .lambda().eq(BillOutgoingsDo::getType, baseInfoVo.getType()));
        Set<String> tagSet = new HashSet<>();
        tagList.forEach(t -> {
            tagSet.addAll(Arrays.asList(t.toString().split(",")));
        });
        OutgoingsPredictVo predictVo = new OutgoingsPredictVo()
                .setDoTimeType(doTimeType)
                .setDoTimeTypeOption(new ArrayList<String>() {
                    {
                        add(BillConstant.DAY_TYPE_WORKDAY);
                        add(BillConstant.DAY_TYPE_WEEKEND);
                        add(BillConstant.DAY_TYPE_HOLIDAY);
                        add(BillConstant.DAY_TYPE_LEAVE);
                    }
                })
                .setTagOption(tagSet);
        if (sameBill != null) {
            predictVo.setTag(Arrays.asList(sameBill.getTag().split(",")))
                    .setIsNecessary(sameBill.getIsNecessary());
        }
        return predictVo;
    }
}
