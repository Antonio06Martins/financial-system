package com.antonio.cashbox.entrypoint.controller.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record SelectBalanceResponse(
        BigDecimal accountBalance,
        LocalDateTime lastUpdate) {
}
