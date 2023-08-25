package com.antonio.pawn.dataprovider.client.response;

import com.antonio.pawn.core.enumeration.TypeBox;
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
