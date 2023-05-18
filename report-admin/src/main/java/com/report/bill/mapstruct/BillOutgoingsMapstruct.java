package com.report.bill.mapstruct;

import com.report.bill.domain.dto.OutgoingsQueryDto;
import com.report.bill.domain.entity.BillOutgoingsDo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsUpdateVo;
import com.report.bill.domain.vo.outgoings.OutgoingsBaseInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wl
 */
@Mapper(componentModel = "spring")
public interface BillOutgoingsMapstruct {

    BillOutgoingsMapstruct INSTANCE = Mappers.getMapper(BillOutgoingsMapstruct.class);

    BillOutgoingsDo updatVoToDo(BillOutgoingsUpdateVo vo);

    OutgoingsQueryDto baseVoToQueryDto(OutgoingsBaseInfoVo vo);
}
