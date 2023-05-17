package com.report.bill.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wl
 */
@Mapper(componentModel = "spring")
public interface BillOutgoingsMapper {

    BillOutgoingsMapper INSTANCE = Mappers.getMapper(BillOutgoingsMapper.class);
}
