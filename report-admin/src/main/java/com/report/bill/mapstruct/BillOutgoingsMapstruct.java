package com.report.bill.mapstruct;

import com.report.bill.domain.entity.BillOutgoingsDo;
import com.report.bill.domain.vo.outgoings.BillOutgoingsUpdateVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wl
 */
@Mapper(componentModel = "spring")
public interface BillOutgoingsMapstruct {

    BillOutgoingsMapstruct INSTANCE = Mappers.getMapper(BillOutgoingsMapstruct.class);

    BillOutgoingsDo updatVoToDo(BillOutgoingsUpdateVo vo);
}
