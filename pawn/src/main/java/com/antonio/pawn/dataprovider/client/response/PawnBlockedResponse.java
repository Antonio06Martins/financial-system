package com.antonio.pawn.dataprovider.client.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PawnBlockedResponse(
        String customerId,
        String nameBox,
        BigDecimal amountBlocked,
        BigDecimal accountBalance,
        String typeBox,
        String statusBox) {
}
