package com.antonio.cashbox.core.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record SelectBalanceDomain(
        BigDecimal accountBalance,
        LocalDateTime lastUpdate) {
}
