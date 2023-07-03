package com.antonio.cashbox.entrypoint.controller.mapper;

import com.antonio.cashbox.core.domain.CashBox;
import com.antonio.cashbox.entrypoint.controller.request.CashBoxRequest;
import com.antonio.cashbox.entrypoint.controller.response.CashBoxResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CashBoxMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "statusBox", ignore = true)
    @Mapping(target = "amountBlocked", ignore = true)
    @Mapping(target = "accountBalance", ignore = true)
    CashBox toCashBox(CashBoxRequest cashBoxRequest, String customerId);

    CashBoxResponse toCashBoxResponse(CashBox cashBox);

}
