package com.report.bill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.report.bill.domain.BillConstant;
import com.report.bill.domain.bo.OutgoingStatisticsBo;
import com.report.bill.domain.dto.OutgoingsDayDto;
import com.report.bill.domain.dto.OutgoingsEditInfoDto;
import com.report.bill.domain.dto.OutgoingsQueryDto;
import com.report.bill.domain.dto.OutgoingsTagDto;
import com.report.bill.domain.entity.OutgoingsDo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsPredictReqVo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsQueryVo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.resp.OutgoingsPredictVo;
import com.report.bill.mapper.BillOutgoingsMapper;
import com.report.bill.mapstruct.BillOutgoingsMapstruct;
import com.report.bill.service.IBillOutgoingsService;
import com.report.common.exception.BusinessException;
import com.report.common.utils.DictUtils;
import com.report.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * @Author: rogers
 */
@Service
public class BillOutgoingsServiceImpl extends ServiceImpl<BillOutgoingsMapper, OutgoingsDo> implements IBillOutgoingsService {

    @Autowired
    private BillOutgoingsMapstruct mapstruct;

    @Autowired
    private BillOutgoingsMapper mapper;

    @Override
    public List<OutgoingsDo> list(OutgoingsQueryVo queryVo) {
        return mapper.getList(toQueryDtoWithOrder(queryVo));
    }

    @Override
    public BigDecimal getAmountSum(OutgoingsQueryVo queryVo) {
        return mapper.getSumTotal(mapstruct.queryVoToDto(queryVo));
    }

    @Override
    public OutgoingsDo getDetail(Long id) {
        return getById(id);
    }

    @Override
    public void add(OutgoingsUpdateVo updateVo) {
        OutgoingsDo outgoingsDo = mapstruct.updateVoToDo(updateVo);
        String dayOfWeek = updateVo.getDoTime().getDayOfWeek().name();
        outgoingsDo.setTag(String.join(",", updateVo.getTag()))
                .setTypeName(DictUtils.getDictLabel(BillConstant.OUTGOINGS_TYPE_DICT, updateVo.getType().toString()))
                .setWeekday(dayOfWeek.charAt(0) + dayOfWeek.toLowerCase().substring(1).toLowerCase());
        save(outgoingsDo);
    }

    @Override
    public OutgoingsEditInfoDto getEditInfo(Long id) {
        OutgoingsDo outgoingsDo = getById(id);
        OutgoingsEditInfoDto dto = mapstruct.editInfoDoToDto(outgoingsDo);
        dto.setTagOption(getTagOptions(Collections.singletonList(dto.getType())));
        return dto;
    }

    @Override
    public void edit(OutgoingsUpdateVo updateVo) {
        OutgoingsDo outgoingsDo = mapstruct.updateVoToDo(updateVo);
        String dayOfWeek = updateVo.getDoTime().getDayOfWeek().name();
        outgoingsDo.setTag(String.join(",", updateVo.getTag()))
                .setTypeName(DictUtils.getDictLabel(BillConstant.OUTGOINGS_TYPE_DICT, updateVo.getType().toString()))
                .setWeekday(dayOfWeek.charAt(0) + dayOfWeek.toLowerCase().substring(1).toLowerCase());
        updateById(outgoingsDo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void del(Long[] ids) {
        try {
            SecurityUtils.getUserId();
        } catch (Exception e) {
            throw new BusinessException("请先登录");
        }
        removeBatchByIds(Arrays.asList(ids));
    }

    @Override
    public OutgoingsPredictVo predictTag(OutgoingsPredictReqVo reqVo) {
        String doTimeType = BillConstant.DOTIME_TYPE_WORKDAY;
        DayOfWeek dayOfWeek = reqVo.getDoTime().getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            doTimeType = BillConstant.DOTIME_TYPE_WEEKEND;
        }

        OutgoingsPredictVo predictVo = new OutgoingsPredictVo()
                .setTagOption(getTagOptions(Collections.singletonList(reqVo.getType())))
                .setDoTimeType(doTimeType);
        // 预测tag
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        OutgoingsQueryDto queryDto = new OutgoingsQueryDto()
                .setAmount(reqVo.getAmount())
                .setType(Collections.singletonList(reqVo.getType()))
                .setDoTimeType(doTimeType)
                .setDoTime(reqVo.getDoTime().format(timeFormatter));
        OutgoingsDo sameBill = mapper.getSameBill(queryDto);
        if (sameBill == null) {
            BigDecimal amountFloat = BigDecimal.valueOf(50);
            int timeFloat = 2;
            if (reqVo.getType() == BillConstant.OUTGOINGS_TYPE_CAR) {
                amountFloat = BigDecimal.valueOf(200);
                timeFloat = 8;
            }
            queryDto.setAmountLow(reqVo.getAmount().subtract(amountFloat))
                    .setAmountHigh(reqVo.getAmount().add(amountFloat))
                    .setTimeStart(reqVo.getDoTime().minusHours(timeFloat).format(timeFormatter))
                    .setTimeEnd(reqVo.getDoTime().plusHours(timeFloat).format(timeFormatter));
            sameBill = mapper.getMostSimilarBill(queryDto);
            if (sameBill == null) {
                queryDto.setAmountLow(null)
                        .setAmountHigh(null);
                sameBill = mapper.getMostSimilarBill(queryDto);
            }
        }
        if (sameBill != null) {
            predictVo.setTag(Arrays.asList(sameBill.getTag().split(",")))
                    .setIsNecessary(Optional.ofNullable(sameBill.getIsNecessary()).orElse(true));
        }
        return predictVo;
    }

    @Override
    public Set<String> getTagOptions(List<Integer> types) {
        List<Object> tagList = listObjs(new QueryWrapper<OutgoingsDo>().select("distinct tag")
                .lambda().in(OutgoingsDo::getType, types));
        Set<String> tagSet = new HashSet<>();
        tagList.forEach(t -> tagSet.addAll(Arrays.asList(t.toString().split(","))));
        return tagSet;
    }

    @Override
    public List<OutgoingsDayDto> listByDay(OutgoingsQueryVo queryVo) {
        List<OutgoingsDayDto> dayList = mapper.getListByDay(toQueryDtoWithOrder(queryVo));
        if (dayList.isEmpty()) {
            return dayList;
        }
        //查询详情
        String day1 = dayList.get(0).getDay().replace("-", "");
        String day2 = dayList.get(dayList.size() - 1).getDay().replace("-", "");
        OutgoingsQueryDto queryDto = new OutgoingsQueryDto().setTimeStart(day1).setTimeEnd(day2);
        if (day1.compareTo(day2) > 0) {
            queryDto.setTimeStart(day2).setTimeEnd(day1);
        }
        queryDto.setType(queryVo.getType());
        queryDto.setOrderString("do_time asc");
        List<OutgoingsDo> detailList = mapper.getList(queryDto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dayList.forEach(d -> d.setDetails(detailList.stream().filter(e -> e.getDoTime().format(formatter).equals(d.getDay()))
                .map(e -> new OutgoingStatisticsBo().setAmount(e.getAmount()).setName(e.getTag()))
                .collect(Collectors.toList())));
        return dayList;
    }

    @Override
    public List<OutgoingsTagDto> listByTag(OutgoingsQueryVo queryVo) {
        OutgoingsQueryDto queryDto = mapstruct.queryVoToDto(queryVo);
        List<OutgoingsTagDto> typeList = mapper.getListByType(queryDto);
        List<OutgoingsTagDto> tagList = mapper.getListByTag(queryDto);
        AtomicInteger id = new AtomicInteger(1);
        typeList.forEach(t -> {
            t.setPid(0);
            t.setId(id.getAndIncrement());
            t.setTag(DictUtils.getDictLabel(BillConstant.OUTGOINGS_TYPE_DICT, t.getType().toString()));
            tagList.forEach(a -> {
                if (a.getType().intValue() == t.getType()) {
                    a.setPid(t.getId());
                    a.setId(id.getAndIncrement());
                }
            });
        });
        typeList.addAll(tagList);
        return typeList;
    }

    /**
     * 处理待排序的列表查询
     *
     * @author wl
     * @date 2023/6/17
     */
    private OutgoingsQueryDto toQueryDtoWithOrder(OutgoingsQueryVo queryVo) {
        OutgoingsQueryDto queryDto = mapstruct.queryVoToDto(queryVo);
        // 处理排序
        if (queryVo.getOrder() != null) {
            Map<String, String> orderMap = queryVo.getOrder();
            String order = orderMap.entrySet().stream().filter(e -> e.getValue() != null).map(e -> e.getKey() + " " + e.getValue()).collect(Collectors.joining(","));
            queryDto.setOrderString(order);
        }
        return queryDto;
    }
}
