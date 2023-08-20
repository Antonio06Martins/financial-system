package com.antonio.cashbox.entrypoint.controller.response;

import com.antonio.cashbox.core.enumeration.TypeBox;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CashBoxResponse(
        String customerId,
        String nameBox,
        TypeBox typeBox,
        LocalDateTime createdIn,
        SelectBlockedBalanceResponse selectBlockedBalance,
        SelectBalanceResponse selectBalance) {
}
