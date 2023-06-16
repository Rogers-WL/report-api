package com.report.bill.mapstruct;

import com.report.bill.domain.dto.OutgoingsDayDto;
import com.report.bill.domain.dto.OutgoingsQueryDto;
import com.report.bill.domain.dto.OutgoingsTagDto;
import com.report.bill.domain.entity.OutgoingsDo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsPredictReqVo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsQueryVo;
import com.report.bill.domain.vo.outgoings.req.OutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.resp.OutgoingsDayVo;
import com.report.bill.domain.vo.outgoings.resp.OutgoingsTagVo;
import com.report.bill.domain.vo.outgoings.resp.OutgoingsVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wl
 */
@Mapper(componentModel = "spring")
public interface BillOutgoingsMapstruct {

    BillOutgoingsMapstruct INSTANCE = Mappers.getMapper(BillOutgoingsMapstruct.class);

    OutgoingsQueryDto queryVoToDto(OutgoingsQueryVo queryVo);

    OutgoingsVo doToVo(OutgoingsDo outgoingsDo);

    List<OutgoingsVo> listDoToVo(List<OutgoingsDo> doList);

    @Mapping(target = "tag", ignore = true)
    OutgoingsDo updateVoToDo(OutgoingsUpdateVo vo);

    @Mapping(target = "doTime", ignore = true)
    OutgoingsQueryDto predictReqVoToQueryDto(OutgoingsPredictReqVo vo);

    List<OutgoingsDayVo> listDayDtoToVo(List<OutgoingsDayDto> list);

    List<OutgoingsTagVo> listTagDtoToVo(List<OutgoingsTagDto> list);
}
