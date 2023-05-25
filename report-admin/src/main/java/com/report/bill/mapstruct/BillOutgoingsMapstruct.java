package com.report.bill.mapstruct;

import com.report.bill.domain.dto.OutgoingsQueryDto;
import com.report.bill.domain.entity.BillOutgoingsDo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsVo;
import com.report.bill.domain.vo.outgoings.OutgoingsBaseInfoVo;
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

    BillOutgoingsVo doToVo(BillOutgoingsDo outgoingsDo);

    List<BillOutgoingsVo> listDoToVo(List<BillOutgoingsDo> doList);

    @Mapping(target = "tag", ignore = true)
    BillOutgoingsDo updateVoToDo(BillOutgoingsUpdateVo vo);

    OutgoingsQueryDto baseVoToQueryDto(OutgoingsBaseInfoVo vo);
}
