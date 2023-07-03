package com.antonio.cashbox.entrypoint.controller.response;

import com.antonio.cashbox.core.enumeration.StatusBox;
import com.antonio.cashbox.core.enumeration.TypeBox;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CashBoxResponse(
        String customerId,
        String nameBox,
        BigDecimal amountBlocked,
        BigDecimal accountBalance,
        TypeBox typeBox,
        StatusBox statusBox) {
}
