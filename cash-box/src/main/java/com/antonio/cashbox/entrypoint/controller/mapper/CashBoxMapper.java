package com.antonio.cashbox.entrypoint.controller.mapper;

import com.antonio.cashbox.core.domain.CashBoxDomain;
import com.antonio.cashbox.entrypoint.controller.response.CashBoxResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CashBoxMapper {

//    @Mapping(target = "selectBlockedBalance", ignore = true)
//    @Mapping(target = "selectBalance", ignore = true)
    List<CashBoxResponse> toCashBoxResponse(List<CashBoxDomain> cashBoxDomain);

}
