package com.antonio.pawn.dataprovider.client.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record SelectBlockedBalanceResponse(
        BigDecimal amountBlocked,
        LocalDateTime lastUpdate) {
}
